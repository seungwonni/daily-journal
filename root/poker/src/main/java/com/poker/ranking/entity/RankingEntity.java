package com.poker.ranking.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Entity
@Table(name="Ranking")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RankingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rankNum;
    //Login 객체에서 셋팅
    private String email;
    private String nickname;
    //RankingRequest 객체에서 셋팅
    private Integer tryCount;
    private String handRanking;
    private Double percent;
    //계산 필요
    private Integer ranking;


}