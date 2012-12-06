package webfunge.ui

import js.dom.core.Node
import js.dom.html.HTMLTableElement
import js.dom.html.HTMLTableRowElement
import js.dom.html.HTMLTableSectionElement
import js.dom.html.window
import webfunge.core.Stack

class StackView(val stack: Stack) {

    val element = window.document.createElement("table") as HTMLTableElement;

    {
        element.className = "stack";
    }

    fun update() {
        element.removeAllChildren()
        val tbody = window.document.createElement("tbody") as HTMLTableSectionElement
        element.appendChild(tbody)

        for (value in stack) {
            tbody.createRow(value)
        }
    }

    fun HTMLTableSectionElement.createRow(value: Int) {
        val row = this.insertRow(0) as HTMLTableRowElement

        val cell1 = row.insertCell(0)!!
        val cell2 = row.insertCell(1)!!

        cell1.textContent = value.toString()
        cell1.className = "number"
        cell2.textContent = "'${value.toChar()}'"
        cell2.className = "char"
    }

    fun Node.removeAllChildren() {
        val children = element.childNodes

        for (i in 0..children.length.toInt()-1) {
            element.removeChild(children.item(i)!!)
        }
    }
}
