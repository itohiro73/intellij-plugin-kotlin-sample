package com.github.itohiro73.intellijpluginkotlinsample.action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.ui.popup.JBPopupFactory
import java.awt.Dimension
import java.awt.event.ActionListener
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers
import javax.swing.JTextField
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject

class ChatGptPopupDialogAction : AnAction() {

    override fun actionPerformed(event: AnActionEvent) {
        val openApiSecret = "THIS IS ONLY FOR LOCAL PLAYGROUND. DON'T COMMIT YOUR SECRET!!"

        val searchField = JTextField()
        searchField.addActionListener(ActionListener { event ->
            val completions = getCompletions(searchField.getText(), "text-davinci-003", openApiSecret)
            val message = completions.replace("\\n", "<br>");
            Messages.showMessageDialog(message, "ChatGPTからの返答", Messages.getInformationIcon())
        })

        val factory = JBPopupFactory.getInstance()
        factory.createComponentPopupBuilder(searchField, searchField)
            .setTitle("ChatGPTへの質問")
            .setMinSize(Dimension(500, 80))
            .setResizable(true)
            .setMovable(true)
            .setRequestFocus(true)
            .createPopup()
            .showCenteredInCurrentWindow(event.project!!)
    }

    fun getCompletions(prompt: String, model: String, apiKey: String): String {
        val endpointUrl = "https://api.openai.com/v1/completions"

        val authHeader = "Bearer " + apiKey

        val jsonStr = "{" +
                "\"model\": \"$model\",\n" +
                "\"prompt\": \"$prompt\",\n" +
                "\"max_tokens\": 4000,\n" +
                "\"temperature\": 0" +
                "}"

        val client = HttpClient.newHttpClient()

        val request: HttpRequest = HttpRequest.newBuilder()
            .uri(URI.create(endpointUrl))
            .POST(HttpRequest.BodyPublishers.ofString(jsonStr))
            .header("Content-Type", "application/json")
            .header("Authorization", authHeader)
            .build()
        val response = client.send(request, BodyHandlers.ofString())

        val json = Json.parseToJsonElement(response.body())
        val text = json.jsonObject["choices"]!!.jsonArray[0].jsonObject["text"].toString()
        return text
    }
}