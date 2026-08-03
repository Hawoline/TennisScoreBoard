package ru.hawoline.tennisscoreboard.domain.model;

import java.util.List;

public class CompletedMatchesResponse {
    private List<CompletedMatchResponse> matches;
    private int currentPage;
    private int totalPages;

    public CompletedMatchesResponse(List<CompletedMatchResponse> matches, int currentPage, int totalPages) {
        this.matches = matches;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
    }

    public List<CompletedMatchResponse> getMatches() {
        return matches;
    }

    public void setMatches(List<CompletedMatchResponse> matches) {
        this.matches = matches;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
