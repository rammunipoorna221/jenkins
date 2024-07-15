from flask import Flask, jsonify, request

app = Flask(_name_)

# Sample data
todos = [
    {"id": 1, "task": "Buy groceries", "done": False},
    {"id": 2, "task": "Read a book", "done": False},
    {"id": 3, "task": "Learn Flask", "done": True},
]

@app.route('/')
def hello_world():
    return 'Hello, World!'

@app.route('/todos', methods=['GET'])
def get_todos():
    return jsonify(todos)

@app.route('/todos', methods=['POST'])
def add_todo():
    new_todo = request.json
    todos.append(new_todo)
    return jsonify(new_todo), 201

@app.route('/todos/<int:todo_id>', methods=['PUT'])
def update_todo(todo_id):
    todo = next((t for t in todos if t['id'] == todo_id), None)
    if todo is None:
        return jsonify({"error": "Todo not found"}), 404
    todo.update(request.json)
    return jsonify(todo)

@app.route('/todos/<int:todo_id>', methods=['DELETE'])
def delete_todo(todo_id):
    global todos
    todos = [t for t in todos if t['id'] != todo_id]
    return '', 204

if _name_ == '_main_':
    app.run(host='0.0.0.0', port=5000)
