const htmltable = document.querySelector("table");
const rows = htmltable.tBodies[0].rows;

for (let i=0;i<rows.length;++i) {
    const student = rows[i].cells[0].textContent;
    const telefon = rows[i].cells[1].textContent;
    console.log(`Student ${student} har telefon ${telefon}`);
}


