# ChitChat User Guide

ChitChat is a chatbot that helps to keep track of tasks and deadlines.

## Adding Todos

Add a todo task to the task list.

Example: `todo read book`
```
Got it. I've added this task:
  [T][ ] read book
Now you have 1 task(s) in the list.
```

## Adding Deadlines

Add a deadline task to the task list.

Example: `deadline assignment /by 2025-02-22 2359`
```
Got it. I've added this task:
  [D][ ] assignment (by: Feb 22 2025, 11:59 PM)
Now you have 2 task(s) in the list.
```

## Adding Events

Add an event task to the task list.

Example: `event meeting /from 2025-02-22 1400 /to 2025-02-22 1600`
```
Got it. I've added this task:
  [E][ ] meeting (from: Feb 22 2025, 2:00 PM to: Feb 22 2025, 4:00 PM)
Now you have 3 task(s) in the list. 
```

## Listing Tasks

List all the tasks in the task list.

Example: `list`
```
Here are the tasks in your list:
1.[T][ ] read book
2.[D][ ] assignment (by: Feb 22 2025, 11:59 PM)
3.[E][ ] meeting (from: Feb 22 2025, 2:00 PM to: Feb 22 2025, 4:00 PM)
```

## Marking and Unmarking Tasks

Mark or unmark a task at given index.

Example: `mark 1`
```
Nice! I've marked this task as done:
  [T][X] read book
```
Example: `unmark 1`
```
OK, I've marked this task as not done yet:
  [T][ ] read book
```

## Finding Tasks

Find a task containing given keyword(s)

Example: `find book`
```
Here are the matching tasks found:
1. [T][ ] read book
```

## Deleting Tasks

Delete a task at given index.

Example: `delete 3`
```
Noted. I've removed this task:
  [E][ ] meeting (from: Feb 22 2025, 2:00 PM to: Feb 22 2025, 4:00 PM)
Now you have 2 task(s) in the list.
```

## Exiting the App

Exit the app on command: `bye`.

## Acknowledgements
chitchatImage.png: https://images.app.goo.gl/9GUZ1y6NvtvQWbCY7
userImage.png: https://images.app.goo.gl/Jkt7WeDkzEcW4uJD6