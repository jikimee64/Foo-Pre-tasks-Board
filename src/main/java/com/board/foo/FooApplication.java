package com.board.foo;

import com.board.foo.domain.Board;
import com.board.foo.repository.BoardRepository;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class FooApplication {
	public static void main(String[] args) {
		SpringApplication.run(FooApplication.class, args);
	}

	//게시판 디폴트 데이터
	@Bean
	public CommandLineRunner runner(BoardRepository boardRepository) throws Exception {
		return (args) -> {
			IntStream.rangeClosed(1, 6).forEach(index -> {
				boardRepository.save(Board.builder()
				.title("제목"+index)
				.contents("내용"+index)
				.writer("foo@foo.com")
				.build()
				);
			});

			IntStream.rangeClosed(7, 12).forEach(index -> {
				boardRepository.save(Board.builder()
					.title("제목"+index)
					.contents("내용"+index)
					.writer("jpa@jpa.com")
					.build()
				);
			});
		};
	}
}


