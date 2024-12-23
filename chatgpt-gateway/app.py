from flask import Flask, request
import logging
from engine.ChatGPT import ChatGPT

app = Flask(__name__)

app.logger.setLevel(logging.DEBUG)

@app.route('/chat', methods=["POST"])
def chat():
    data = request.json
    app.logger.info(str(data))

    task = data.get("task")
    prompt = data.get("prompt")
    prompt_input = data.get("promptInput")
    instructions = data.get("instructions")
    observations = data.get("observations")
    examples = data.get("examples")

    chatgpt = ChatGPT()

    result = {}

    if request.is_json:
        app.logger.info(f"Got parameters: task={task}, prompt={prompt}")
        result = chatgpt.interact(task, prompt, prompt_input, instructions, observations, examples)

    return result


if __name__ == '__main__':
    app.run(threaded=True, debug=False, host='0.0.0.0', port=5000)
