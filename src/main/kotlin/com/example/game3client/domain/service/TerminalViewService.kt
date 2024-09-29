package com.example.game3client.domain.service

import com.example.game3client.domain.view.ViewDrawable
import org.springframework.stereotype.Service

@Service
class TerminalViewService: ViewDrawable {

    enum class AnsiCode(val value: String) {
       Clear("\u001b[H\u001b[2J")
    }

    override fun printMessage(msg: String) {
        println(msg)
    }

    override fun clear() {
        print(AnsiCode.Clear.value)
    }
}