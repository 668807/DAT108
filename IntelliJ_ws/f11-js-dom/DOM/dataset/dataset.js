const kurslement = document.querySelector("p[data-kurs]");

const kurs = kurslement.dataset.kurs;

const tema = kurslement.dataset.kursTema;  // data-tema
console.log(kurslement);

kurslement.textContent = `Velkommen til delen om ${tema} i ${kurs}`;
