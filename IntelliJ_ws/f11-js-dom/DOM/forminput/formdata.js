const root = document.getElementById("root");

const datecontainer = root.querySelector("fieldset[data-date]");
datecontainer.querySelector("button").addEventListener("click", () => {
    const datevalue = datecontainer.querySelector("input").valueAsNumber;
    console.log(`${datevalue} - ${Date.UTC(2026,8,25,0, 0, 0, 0)}`);
});

const datetimelocalcontainer = root.querySelector("fieldset[data-datetime-local]");
datetimelocalcontainer.querySelector("button").addEventListener("click", () => {
    const datevalue = datetimelocalcontainer.querySelector("input").valueAsNumber;
    console.log(`${datevalue} - ${Date.UTC(2026,8,25,23, 15, 27, 0)}`);
});

const month = root.querySelector("fieldset[data-month]");
month.querySelector("button").addEventListener("click", () => {
    const datevalue = month.querySelector("input").valueAsNumber;
    console.log(`${datevalue} - ${Date.UTC(2026,8,0,0, 0, 0, 0)}`);
});

const time = root.querySelector("fieldset[data-time]");
time.querySelector("button").addEventListener("click", () => {
    const datevalue = time.querySelector("input").valueAsNumber;
    console.log(`${datevalue} - ${Date.UTC(1970,0,1,14, 37, 21, 0)}`);
});

const week = root.querySelector("fieldset[data-week]");
week.querySelector("button").addEventListener("click", () => {
    const datevalue = week.querySelector("input").valueAsNumber;
    console.log(`${datevalue} - ${Date.UTC(2026,8,21,0, 0, 0, 0)}`);
});