package com.example.riotdo.REST_Board.domain.boards;

import com.example.riotdo.REST_Board.Entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Integer> {
}
