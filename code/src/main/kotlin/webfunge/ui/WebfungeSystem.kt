package webfunge.ui

import js.dom.html.HTMLButtonElement
import js.dom.html.HTMLElement
import js.dom.html.HTMLTextAreaElement
import js.dom.html.window
import webfunge.core.Interpreter

class WebfungeSystem(val root: HTMLElement) {

    val overviewArea = window.document.createElement("div") as HTMLElement
    val buttonsArea = window.document.createElement("div") as HTMLElement
    val console = TextAreaUserConsole()
    val textArea = createProgramArea()
    val interpreter = Interpreter(console)
    val stepButton = button("step") { step() }
    val step100Button = button("step 100") { step(100) }
    val step1000Button = button("step 1000") { step(1000) }
    val step10000Button = button("step 10000") { step(10000) }
    val clearButton = button("clear") { clear() };

    {
        root.appendChild(overviewArea)
        root.appendChild(textArea)
        root.appendChild(buttonsArea)
        root.appendChild(console.element)

        buttonsArea.appendChild(button("load") { load() })
        buttonsArea.appendChild(stepButton)
        buttonsArea.appendChild(step100Button)
        buttonsArea.appendChild(step1000Button)
        buttonsArea.appendChild(step10000Button)
        buttonsArea.appendChild(clearButton)
        updateUI()
    }

    fun button(text: String, func: () -> Unit): HTMLButtonElement {
        val button = window.document.createElement("button") as HTMLButtonElement
        button.textContent = text
        button.onclick = func
        return button
    }

    fun load() {
        interpreter.load(textArea.value)
        updateUI()
    }

    fun step(max: Int = 1) {
        interpreter.step(max)
        updateUI()
    }

    fun clear() {
        console.clear()
        updateUI()
    }

    fun updateUI() {
        stepButton.disabled = interpreter.finished
        step100Button.disabled = interpreter.finished
        step1000Button.disabled = interpreter.finished
        step10000Button.disabled = interpreter.finished
        clearButton.disabled = console.isEmpty
        overviewArea.textContent = "direction: ${interpreter.direction}, pos: ${interpreter.x}, ${interpreter.y}, stack size: ${interpreter.stack.size}, steps: ${interpreter.steps}"
    }
}

fun initSystem(root: HTMLElement) {
    WebfungeSystem(root)
}


fun createProgramArea(): HTMLTextAreaElement {
    val example = """  9::*\2*+00p0v"."0<
>310p0","     >"llaw eht no "v  >#v_ ^
^_210p0"--:"                 v  ,
 :    v     " of beer"       <  :
 -                >"selttob"00g.^ <     <
 1         >00g1-#^_$" elttob erom enO" ^
      >00g#^_$" selttob erom oN"        ^
 ^_110p0",dnuora ti ssap ,nwod eno ekaT"^
  ^:-1_010p00g1-00pvv:-1g01_@#g00,*25<
      ^             <

    """

    val area = window.document.createElement("textarea") as HTMLTextAreaElement
    area.rows = 25.toDouble()
    area.cols = 80.toDouble()
    area.value = example
    return area
}
