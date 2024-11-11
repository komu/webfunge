package webfunge.ui

import kotlinx.browser.document
import org.w3c.dom.HTMLElement

fun main() {
    val root = document.createElement("div") as HTMLElement
    document.body?.appendChild(root)

    WebfungeSystem(root)
}

