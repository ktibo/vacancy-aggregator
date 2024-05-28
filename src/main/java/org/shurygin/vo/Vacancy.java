package org.shurygin.vo;

import java.util.Objects;

public class Vacancy {

    private String title;
    private String experience;
    private String city;
    private String companyName;
    private String siteName;
    private String url;
    private Compensation compensation;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Compensation getCompensation() {
        return compensation;
    }

    public void setCompensation(int from, int to, String currencyCode, boolean gross) {
        this.compensation = new Compensation(from, to, currencyCode, gross);
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Vacancy{" +
                title +
                ", experience='" + experience + '\'' +
                ", compensation=" + compensation +
                ", city='" + city + '\'' +
                ", url='" + url + '\'' +
                ", company='" + companyName + '\'' +
                ", site='" + siteName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vacancy vacancy)) return false;
        if (!Objects.equals(title, vacancy.title)) return false;
        //if (!Objects.equals(salary, vacancy.salary)) return false;
        if (!Objects.equals(city, vacancy.city)) return false;
        if (!Objects.equals(companyName, vacancy.companyName)) return false;
        if (!Objects.equals(siteName, vacancy.siteName)) return false;
        return Objects.equals(url, vacancy.url);
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        //result = 31 * result + (salary != null ? salary.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (companyName != null ? companyName.hashCode() : 0);
        result = 31 * result + (siteName != null ? siteName.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }

    public String getSalary() {
        return compensation == null ? "" : compensation.toString();
    }

    class Compensation{
        private int from;
        private int to;
        private String currencyCode;
        private boolean gross;

        public Compensation(int from, int to, String currencyCode, boolean gross) {
            this.from = from;
            this.to = to;
            this.currencyCode = currencyCode;
            this.gross = gross;
        }

        @Override
        public String toString() {
            return "(" + from + " - " + to + ", " + currencyCode + ", " + gross + ')';
        }
    }

}
