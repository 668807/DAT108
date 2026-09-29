const root = document.getElementById("root");

root.getElementsByTagName("button")[0].addEventListener("click", vistall);

function vistall() {
    const tallElement = root.getElementsByTagName("input")[0];
    const tallSomStreng = tallElement.value;

    resultatElement = root.getElementsByTagName("p")[0];
    if (tallSomStreng !== "") {
        resultatElement.getElementsByTagName("span")[0].textContent =
            tallSomStreng;
        resultatElement.classList.remove("hidden");
    } else {
        resultatElement.classList.add("hidden");
    }
}