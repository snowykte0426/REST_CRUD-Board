package com.example.riotdo.REST_Board.domain.boards;

import com.example.riotdo.REST_Board.domain.users.User;
import com.example.riotdo.REST_Board.domain.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional(readOnly = true)
    public List<BoardDto> getBoards() {
        List<Board> boards = boardRepository.findAll();
        List<BoardDto> boardDtos = new ArrayList<>();
        boards.forEach(s -> boardDtos.add(BoardDto.toDto(s)));
        return boardDtos;
    }

    @Transactional(readOnly = true)
    public BoardDto getBoard(int id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Board Id를 찾을 수 없습니다."));
        return BoardDto.toDto(board);
    }

    @Transactional
    public BoardDto write(BoardDto boardDto, User user) {
        Board board = Board.builder().title(boardDto.getTitle()).content(boardDto.getContent()).user(user).build();
        boardRepository.save(board);
        return BoardDto.toDto(board);
    }

    @Transactional
    public BoardDto update(int id, BoardDto boardDto, User user) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Board Id를 찾을 수 없습니다!"));

        Board updatedBoard = board.toBuilder().title(boardDto.getTitle()).content(boardDto.getContent()).user(user).build();
        boardRepository.save(updatedBoard);

        return BoardDto.toDto(updatedBoard);
    }

    @Transactional
    public void delete(int id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Board Id를 찾을 수 없습니다!"));
        boardRepository.deleteById(id);
    }
}