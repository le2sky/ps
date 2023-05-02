const readline = require("readline");

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

let input = [];

rl.on("line", function (line) {
  input.push(line);
}).on("close", function () {
  const [n, k] = input.shift().split(" ").map(Number);
  solution(n, input[0].split(" ").map(Number), k);
  process.exit();
});

function solution(n, numbers, k) {
  const plug = [];
  let count = 0;
  let result = 0;
  for (let i = 0; i < k; i++) {
    const v = numbers.shift();
    if (plug.includes(v)) continue;
    if (count < n) {
      plug.push(v);
      count++;
    } else {
      let isPlugIn = false;
      let lastArr = [];
      for (let j = 0; j < plug.length; j++) {
        const item = plug[j];
        if (item == null) continue;
        if (!numbers.includes(item)) {
          isPlugIn = true;
          delete plug[j];
          plug.push(v);
          result++;
          break;
        } else {
          //현재 플러그 값이 numbers에서 첫번쨰로 등장하는 위치
          lastArr.push(numbers.indexOf(item));
        }
      }
      if (!isPlugIn) {
        // 등장하는 제품들 중 남을 것들 중에서 가장 마지막으로 등장하는 값 삭제
        delete plug[plug.indexOf(numbers[Math.max(...lastArr)])];
        plug.push(v);
        result++;
      }
    }
  }
  console.log(result);
}
