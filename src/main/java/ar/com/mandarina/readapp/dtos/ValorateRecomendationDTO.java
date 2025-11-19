package ar.com.mandarina.readapp.dtos;

public class ValorateRecomendationDTO {
    private Long userId;
    private Long recommendationId;
    private Long rate;
    private String comment;

    public ValorateRecomendationDTO(Long userId, Long recommendationId, Long rate, String comment) {
        this.userId = userId;
        this.recommendationId = recommendationId;
        this.rate = rate;
        this.comment = comment;
    }

    public ValorateRecomendationDTO() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRecommendationId() {
        return recommendationId;
    }

    public void setRecommendationId(Long recommendationId) {
        this.recommendationId = recommendationId;
    }

    public Long getRate() {
        return rate;
    }

    public void setRate(Long rate) {
        this.rate = rate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
