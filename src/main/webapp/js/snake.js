let canvas = document.getElementById("game");
let context = canvas.getContext("2d");
let score = 0;
let grid = 16;
let count = 0;
let maxscore = 0;
let timeSpent = 0;
let snake = {
    x: 160,
    y: 160,

    // snake velocity. moves one grid length every frame in either the x or y direction
    dx: grid,
    dy: 0,

    // keep track of all grids the snake body occupies
    cells: [],

    // length of the snake. grows when eating an apple
    maxCells: 4
};
let apple = {
    x: 320,
    y: 320
};

let obstacles = [];
function generateObstacles(numObstacles) {
    for (let i = 0; i < numObstacles; i++) {
        if(obstacles.length < 100){
            let newObstacle;
            do {
                newObstacle = {
                    x: getRandomInt(0, 25) * grid,
                    y: getRandomInt(0, 25) * grid
                };
            }while((newObstacle.x === apple.x && newObstacle.y === apple.y) || (newObstacle.x === snake.x && newObstacle.y === snake.y) || obstacles.some(obstacle => obstacle.x === newObstacle.x && obstacle.y === newObstacle.y));
            obstacles.push({
                x: getRandomInt(0, 25) * grid,
                y: getRandomInt(0, 25) * grid
            });
        }
    }
}

generateObstacles(4);

function generateApple() {
    let newApple;
    do {
        newApple = {
            x: getRandomInt(0, 25) * grid,
            y: getRandomInt(0, 25) * grid
        };
    } while (obstacles.some(obstacle => obstacle.x === newApple.x && obstacle.y === newApple.y) || (newApple.x === snake.x && newApple.y === snake.y));
    apple = newApple;
}
function reset(){
    snake.x = 160;
    snake.y = 160;
    snake.cells = [];
    snake.maxCells = 4;
    snake.dx = grid;
    snake.dy = 0;
    if(maxscore<score){
        maxscore = score;
    }
    score = 0;
    updateScore(score);
    apple.x = getRandomInt(0, 25) * grid;
    apple.y = getRandomInt(0, 25) * grid;
    obstacles = [];
    generateObstacles(4);
}
// get random whole numbers in a specific range
function getRandomInt(min, max) {
    return Math.floor(Math.random() * (max - min)) + min;
}
function updateScore(score){
    document.getElementById("score").value = score;
    document.getElementById("maxscore").value = maxscore;
}
function updateTime() {
    setInterval(function() {
        timeSpent++;
        document.getElementById("time").value = timeSpent;
    }, 1000); // 1 second = 1000 milliseconds
}

// game loop
function loop() {
    requestAnimationFrame(loop);

    // slow game loop to 15 fps instead of 60 (60/15 = 4)
    if (++count < 6) {
        return;
    }

    count = 0;
    context.clearRect(0, 0, canvas.width, canvas.height);

    // move snake by its velocity
    snake.x += snake.dx;
    snake.y += snake.dy;

    // wrap snake position horizontally on edge of screen
    if (snake.x < 0) {
        reset();
    } else if (snake.x >= canvas.width) {
        reset();
    }

    // wrap snake position vertically on edge of screen
    if (snake.y < 0) {
        reset();
    } else if (snake.y >= canvas.height) {
        reset();
    }

    // keep track of where snake has been. front of the array is always the head
    snake.cells.unshift({ x: snake.x, y: snake.y });

    // remove cells as we move away from them
    if (snake.cells.length > snake.maxCells) {
        snake.cells.pop();
    }

    // draw apple

    context.fillStyle = "red";
    context.fillRect(apple.x, apple.y, grid - 1, grid - 1);

    // draw obstacles

    obstacles.forEach(function(obstacle) {
        context.fillStyle = 'blue';
        context.fillRect(obstacle.x, obstacle.y, grid - 1, grid - 1);
    });

    // draw snake one cell at a time

    context.fillStyle = "#F5C469";
    snake.cells.forEach(function(cell, index) {
        // drawing 1 px smaller than the grid creates a grid effect in the snake body, so you can see how long it is
        context.fillRect(cell.x, cell.y, grid - 1, grid - 1);

        // snake ate apple
        if (cell.x === apple.x && cell.y === apple.y) {
            snake.maxCells++;

            generateApple();
            generateObstacles(1);

            score++;
            updateScore(score);
        }

        // check collision with all cells after this one (modified bubble sort)
        for (let i = index + 1; i < snake.cells.length; i++) {
            // snake occupies same space as a body part. reset game
            if (cell.x === snake.cells[i].x && cell.y === snake.cells[i].y) {
                reset();
            }
        }

        // check collision with obstacles

        for (let i = 0; i < obstacles.length; i++) {
            if (snake.x === obstacles[i].x && snake.y === obstacles[i].y) {
                reset();
            }
        }
    });
}

// listen to keyboard events to move the snake
document.addEventListener("keydown", function(e) {
    if (e.key === 'ArrowLeft' && snake.dx === 0) {
        snake.dx = -grid;
        snake.dy = 0;
    }
    else if (e.key === 'ArrowUp' && snake.dy === 0) {
        snake.dy = -grid;
        snake.dx = 0;
    }
    else if (e.key === 'ArrowRight' && snake.dx === 0) {
        snake.dx = grid;
        snake.dy = 0;
    }
    else if (e.key === 'ArrowDown' && snake.dy === 0) {
        snake.dy = grid;
        snake.dx = 0;
    }
});

// start the game
updateTime();
requestAnimationFrame(loop);