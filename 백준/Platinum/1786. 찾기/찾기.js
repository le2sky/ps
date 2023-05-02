
const readline = require("readline");

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

let input = [];

rl.on("line", function (line) {
  input.push(line);
}).on("close", function () {
  solution(input[0], input[1]);
  process.exit();
});

function makeTable(pattern) {
  const table = Array.from({ length: pattern.length }, () => 0);
  let j = 0;
  for (let i = 1; i < pattern.length; i++) {
    while (j > 0 && pattern[i] !== pattern[j]) {
      //j에서 1을 뺸 값이 가리키는 인덱스로 이동
      j = table[j - 1];
    }
    if (pattern[i] == pattern[j]) {
      // j 위치에 1을 더한다.
      table[i] = ++j;
    }
  }
  return table;
}

function kmp(parent, pattern) {
  const table = makeTable(pattern);
  let result = 0;
  let index = [];
  let j = 0;
  for (let i = 0; i < parent.length; i++) {
    while (j > 0 && parent[i] != pattern[j]) {
      j = table[j - 1];
    }
    if (parent[i] == pattern[j]) {
      if (j == pattern.length - 1) {
        index.push(i - pattern.length + 2);
        j = table[j];
        result++;
      } else {
        j++;
      }
    }
  }
  return [result, index];
}

function solution(str, pattern) {
  const [result, index] = kmp(str, pattern);
  console.log(result);
  console.log(index.reduce((acc, v) => acc + v + " ", "").trim());
}
