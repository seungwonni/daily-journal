package com.poker.ranking.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@Entity
@Table(name="RANKING")
public class RankingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rankNum;
    private String email;
    private Integer ranking;
    private Integer tryCount;
    private String handRanking;
}