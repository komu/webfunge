package webfunge

import js.dom.html.HTMLButtonElement
import js.dom.html.HTMLElement
import js.dom.html.HTMLTextAreaElement
import js.dom.html.window

class WebfungeSystem(val textArea: HTMLTextAreaElement,
                     val console: UserConsole,
                     val overViewArea: HTMLElement,
                     val buttonsArea: HTMLElement) {

    val interpreter = Interpreter(console)
    val stepButton = button("step") { step() }
    val step100Button = button("step 100") { step(100) }
    val step1000Button = button("step 1000") { step(1000) }
    val step10000Button = button("step 10000") { step(10000) }
    val clearButton = button("clear") { clear() };

    {
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
        overViewArea.textContent = "direction: ${interpreter.direction}, pos: ${interpreter.x}, ${interpreter.y}, stack size: ${interpreter.stack.size}, steps: ${interpreter.steps}"
    }
}

fun initialize(programAreaId: String, outputAreaId: String, overviewAreaId: String, buttonsAreaId: String): WebfungeSystem {
    val textArea = window.document.getElementById(programAreaId) as HTMLTextAreaElement

    val console = UserConsole(outputAreaId)
    val overviewArea = window.document.getElementById(overviewAreaId) as HTMLElement
    val buttonsArea = window.document.getElementById(buttonsAreaId) as HTMLElement

    return WebfungeSystem(textArea, console, overviewArea, buttonsArea)
}
