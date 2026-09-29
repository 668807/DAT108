const element = document.querySelector("h1:first-child");

const oldContent = element.textContent;

console.log(`${element.tagName} var: ${oldContent}`);

element.textContent = "Velkommen til <var>DAT108</var>";