package com.board.foo.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "board")
public class Board extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 2000)
    private String contents;

    private String writer;

    private Integer hit;

    @Builder
    public Board(String title, String contents, String writer, String createdBy,
        String updatedBy) {
        this.title = title;
        this.contents = contents;
        this.writer = writer;
        this.hit = 0;
        super.setCreatedBy(createdBy);
        super.setUpdatedBy(updatedBy);
    }

    public void changeBoard(String title, String contents){
        this.title = title;
        this.contents = contents;
    }
}
