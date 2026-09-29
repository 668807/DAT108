"use strict";

function demo0() {
    const rootElement =  document.getElementById("demoRoot");

    // p-element med velkomst
    const elmRef = rootElement.querySelector('p[data-welcome]');
    console.log(`Elementet har ${elmRef.classList.length} navn i sitt class attributt`);
}

function demo1() {
    const rootElement =  document.getElementById("demoRoot");

    // p-element med velkomst
    const elmRef = rootElement.querySelector('p[data-welcome]');
    const inList = elmRef.classList.toggle("details");
    console.log("Har 'togglet' class navn 'details' for element");
    if (inList) {
        console.log("Element ble lagt til i listen");
    } else {
        console.log("Element ble fjernet fra listen");
    }
}

function demo2() {
    const rootElement =  document.getElementById("demoRoot");

    // p-element med velkomst
    const elmRef = rootElement.querySelector('p[data-welcome]');
    const inList = elmRef.classList.toggle("example");
    console.log("Har 'togglet' class navn 'example' for element");
    if (inList) {
        console.log("Element ble lagt til i listen");
    } else {
        console.log("Element ble fjernet fra listen");
    }
}

function demo3() {
    const rootElement =  document.getElementById("demoRoot");

    // p-element med velkomst
    const elmRef = rootElement.querySelector('p[data-welcome]');
    const inList = elmRef.classList.toggle("webdisplay");
    console.log("Har 'togglet' class navn 'webdisplay' for element");
    if (inList) {
        console.log("Element ble lagt til i listen");
    } else {
        console.log("Element ble fjernet fra listen");
    }
}

function demo4() {
    const rootElement =  document.getElementById("demoRoot");

    // p-element med velkomst
    const elmRef = rootElement.querySelector('p[data-welcome]');
    if (elmRef.classList.contains("details")) {
        console.log("Elementer har 'details' i sitt class-attributt");
    } else {
        console.log("Elementer har ikke 'details' i sitt class-attributt");
    }
}

function demo5() {
    const rootElement =  document.getElementById("demoRoot");

    // p-element med velkomst
    const elmRef = rootElement.querySelector('p[data-welcome]');
    if (elmRef.classList.contains("example")) {
        console.log("Elementer har 'example' i sitt class-attributt");
    } else {
        console.log("Elementer har ikke 'example' i sitt class-attributt");
    }
}

function demo6() {
    const rootElement =  document.getElementById("demoRoot");

    // p-element med velkomst
    const elmRef = rootElement.querySelector('p[data-welcome]');
    if (elmRef.classList.contains("webdisplay")) {
        console.log("Elementer har 'webdisplay' i sitt class-attributt");
    } else {
        console.log("Elementer har ikke 'webdisplay' i sitt class-attributt");
    }
}

function demo7() {
    const rootElement =  document.getElementById("demoRoot");

    // p-element med velkomst
    const elmRef = rootElement.querySelector('p[data-welcome]');
    Array.from(elmRef.classList).forEach(
        (className,index) => {
            console.log(`Element har class navn '${className}' med indeks ${index}`);
        }
    );
}

function demo8() {
    const rootElement =  document.getElementById("demoRoot");

    // p-element med velkomst
    const elmRef = rootElement.querySelector('p[data-welcome]');
    for (let i=0;i<elmRef.classList.length;++i) {
        console.log(`classList[${i}] er '${elmRef.classList[i]}'`);
    }
}

function demo9() {
    const rootElement =  document.getElementById("demoRoot");

    // p-element med velkomst
    const elmRef = rootElement.querySelector('p[data-welcome]');
    console.log("Fjerner alle class navn fra element");
    elmRef.classList.remove(...elmRef.classList.values());
}

function demo10() {
    const rootElement =  document.getElementById("demoRoot");

    // p-element med velkomst
    const elmRef = rootElement.querySelector('p[data-welcome]');

    while (elmRef.classList.length > 0) {
        console.log(`Fjerner class navn '${elmRef.classList[0]}' fra element`);
        elmRef.classList.remove(elmRef.classList[0]);
    }
}

function demo11() {
    const rootElement =  document.getElementById("demoRoot");

    // p-element med velkomst
    const elmRef = rootElement.querySelector('p[data-welcome]');
    console.log("Fjerner alle class navn fra element");
    elmRef.className = null;
}

function demo12() {
    const rootElement =  document.getElementById("demoRoot");

    // p-element med velkomst
    const elmRef = rootElement.querySelector('p[data-welcome]');
    elmRef.classList.add("details");
    console.log("Har lagt til class navn 'details' for element");
}

function demo13() {
    const rootElement =  document.getElementById("demoRoot");

    // p-element med velkomst
    const elmRef = rootElement.querySelector('p[data-welcome]');
    elmRef.classList.add("example");
    console.log("Har lagt til class navn 'example' for element");
}

function demo14() {
    const rootElement =  document.getElementById("demoRoot");

    // p-element med velkomst
    const elmRef = rootElement.querySelector('p[data-welcome]');
    elmRef.classList.add("webdisplay");
    console.log("Har lagt til class navn 'webdisplay' for element");
}

const demomanager = {
    init(demoHandler) {
        this.demoHandler = demoHandler;
        this.viewcode = this.viewcode.bind(this);

        /**
        * Legger til lytter for hendelse 'click' på alle HTML BUTTON elementer.
        * Ved 'click' på en HTML BUTTON kjøres flere metoder:
        * 1. console.clear som tømmer konsollet
        * 3. En metode gitt av elementet sitt attributt 'data-demo'.
        * 2. Objektet sin metode 'viewcode' som viser kilden for metoden i punktet over.
        * Verdien til 'data-demo' er 'demo0' for første BUTTON, 'demo1' for neste osv.
        */

        const buttons = this.demoHandler.getElementsByTagName("button");
        Array.from(buttons).forEach(
            (button) => {
                const methodName = button.getAttribute("data-demo");
                if (window[methodName] === undefined) return;
                const method = window[methodName];
                if (typeof method !== "function") return;
                button.addEventListener('click', console.clear, true);
                button.addEventListener('click', this.viewcode, true);
                button.addEventListener("click", method);
            }
        )
    },

    viewcode(event) {
        const viewElm = this.demoHandler.querySelector("pre[data-viewelm]");
        const methodName = event.target.getAttribute("data-demo");
        if (window[methodName] === undefined) return;

        const functext = window[methodName].toString();
        viewElm.textContent = functext;
        viewElm.classList.add("demo");
    }
}

function init() {
    const rootDemoHandler = document.getElementById("demoHandler");
    demomanager.init(rootDemoHandler);
}
document.addEventListener('DOMContentLoaded', init);
