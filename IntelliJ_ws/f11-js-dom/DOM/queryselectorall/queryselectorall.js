const elements = document.getElementById("root")
//	.querySelectorAll("<>img");
	.querySelectorAll("body *");
console.log(`Dokumentet inneholder ${elements.length} HTML elementer med tagg img`);

//console.log(`BODY inneholder ${elements.length} HTML elementer`);

elements.forEach(
	e => {
	console.log(`Tagg ${e.tagName}: ${e.textContent}`)
});
