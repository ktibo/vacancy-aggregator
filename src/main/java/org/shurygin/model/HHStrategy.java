package org.shurygin.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.shurygin.vo.Vacancy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

public class HHStrategy implements Strategy {

    private static final String URL_FORMAT = "https://hh.ru/search/vacancy?text=%s&page=%d";

    @Override
    public List<Vacancy> getVacancies(String searchString, int max) {

        List<Vacancy> vacancies = new ArrayList<>();
        Document document;
        int page = 0;
        while (max == 0 || vacancies.size() < max) {

            System.out.println("page: "+page);

            try {
                document = getDocument(searchString, page++);
            } catch (HttpStatusException e) {
                break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String str = document.getElementById("HH-Lux-InitialState").childNodes().get(0).toString();
            JsonNode jsonNode;
            try {
                jsonNode = new ObjectMapper().readTree(str);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            JsonNode jsonNodeVacancies = jsonNode.get("vacancySearchResult").get("vacancies");

            if (jsonNodeVacancies.isEmpty()) break;

            for (JsonNode node : jsonNodeVacancies) {

                Vacancy vacancy = new Vacancy();
                vacancy.setTitle(node.get("name").textValue());
                vacancy.setCompanyName(getField(node.get("company").get("name")));
                vacancy.setCity(getField(node.get("area").get("name")));
                vacancy.setExperience(getField(node.get("workExperience")));
                vacancy.setUrl(getField(node.get("links").get("desktop")));
                vacancy.setSiteName(getField(node.get("company").get("companySiteUrl")));

                JsonNode compensation = node.get("compensation");
                if (!compensation.has("noCompensation")){
                    int from = compensation.get("from") == null ? 0 : compensation.get("from").asInt();
                    int to = compensation.get("to") == null ? 0 : compensation.get("to").asInt();
                    String currencyCode = getField(compensation.get("currencyCode"));
                    boolean gross = compensation.get("gross") != null && compensation.get("gross").asBoolean();
                    vacancy.setCompensation(from, to, currencyCode, gross);
                }

                vacancies.add(vacancy);

            }
        }

        return vacancies;

    }

    private String getField(JsonNode jsonNode) {
        return jsonNode == null ? "" : jsonNode.textValue();
    }

    protected Document getDocument(String searchString, int page) throws IOException {
        return Jsoup.connect(String.format(URL_FORMAT, searchString, page))
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Safari/537.36")
                .referrer("https://hh.ru/")
                .get();
    }


}
