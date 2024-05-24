package david_seu.snake_game.domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Game {

    private int id;
    private int userId;
    private int score;
    private String snakePositions;
}
