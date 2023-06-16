//https://www.acmicpc.net/problem/3197
const readline = require("readline");

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

const dx = [-1, 1, 0, 0];
const dy = [0, 0, -1, 1];
let input = [];

rl.on("line", function (line) {
  input.push(line);
}).on("close", function () {
  const [R, C] = input.shift().split(" ").map(Number);

  solution(
    R,
    C,
    input.map((v) => [...v])
  );

  process.exit();
});

class Queue {
  constructor() {
    this.queue = [];
    this.front = 0;
    this.rear = 0;
  }
  enqueue(value) {
    this.queue[this.rear++] = value;
  }
  dequeue() {
    const value = this.queue[this.front];
    delete this.queue[this.front++];
    return value;
  }
  isEmpty() {
    return this.front == this.rear;
  }
}

function melting(waterQueue, liver, R, C) {
  const next = new Queue();
  while (!waterQueue.isEmpty()) {
    const [x, y] = waterQueue.dequeue();
    for (let i = 0; i < 4; i++) {
      const nx = x + dx[i];
      const ny = y + dy[i];
      if (nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
      if (liver[nx][ny] === "X") {
        liver[nx][ny] = ".";
        next.enqueue([nx, ny]);
      }
    }
  }
  return next;
}

//if duck found then return false, else then return object(true)
function notFoundDuck(duckQueue, liver, visited, R, C) {
  const next = new Queue();
  while (!duckQueue.isEmpty()) {
    const [x, y] = duckQueue.dequeue();
    for (let i = 0; i < 4; i++) {
      const nx = x + dx[i];
      const ny = y + dy[i];

      if (nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
      if (visited[nx][ny]) continue;
      if (liver[nx][ny] === ".") {
        visited[nx][ny] = true;
        duckQueue.enqueue([nx, ny]);
      } else if (liver[nx][ny] === "X") next.enqueue([x, y]);
      else if (liver[nx][ny] === "L") return false;
    }
  }
  return next;
}

function solution(R, C, liver) {
  let duckQueue = new Queue();
  let waterQueue = new Queue();
  let isFounded = false;
  let result = 0;
  const visited = Array.from(Array(R), () => Array(C).fill(false));

  //setting
  for (let i = 0; i < R; i++) {
    for (let j = 0; j < C; j++) {
      if (liver[i][j] === ".") waterQueue.enqueue([i, j]);
      else if (!isFounded && liver[i][j] === "L") {
        liver[i][j] = ".";
        visited[i][j] = true;
        isFounded = true;
        waterQueue.enqueue([i, j]);
        duckQueue.enqueue([i, j]);
      } else if (isFounded && liver[i][j] === "L") {
        waterQueue.enqueue([i, j]);
      }
    }
  }

  //play logic
  while (true) {
    duckQueue = notFoundDuck(duckQueue, liver, visited, R, C);
    if (!duckQueue) {
      console.log(result);
      break;
    }
    waterQueue = melting(waterQueue, liver, R, C);
    result++;
  }
}
