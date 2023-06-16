const readline = require("readline");
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

const input = [];
rl.on("line", function (line) {
  input.push(line);
}).on("close", function () {
  solution(Number(input[0]), input[1].split(" ").map(Number));
  process.exit();
});

function solution(n, arr) {
  const dp = Array(n + 1).fill(0);
  arr.unshift(0);
  dp[1] = arr[1];

  for (let i = 2; i <= n; i++) {
    for (let j = 1; j <= i; j++) {
      dp[i] = Math.max(dp[i], dp[i - j] + arr[j]);
    }
  }

  console.log(dp[n]);
}
