# Pelops II 

> "Hello from Pelops II. This computer has little space for me. Let me organize the data inside." - Pelops II

Welcome to Pelops II, your task management companion! 
This application helps you keep track of your _to-dos, deadlines, and events_ with ease.

## Features

Here's a quick rundown of what Pelops II can do:

* Add **To-Do** tasks
* Create tasks with specific **Deadlines** 
* Schedule **Events** with start and end times.
* Mark tasks as done or not done ✅
* Delete unwanted tasks
* Search for tasks

## How to Use

Follow these steps to get started with Pelops II:

1.  Download the latest release from [source](https://github.com/SuspectBlue/ip).
2.  Run the application from your terminal.
3.  Enter commands like: `todo <description>`, `deadline <description> /by <date/time>`, `event <description> /from <start_time> /to <end_time>`, `list`, `mark <index>`, `unmark <index>` and `delete <index>` to manage your tasks.

If you are a Java programmer, you can use it to practice Java too. Here's the main method:
``` java
public class Main {
    public static void main(String[] args) throws IOException {
        new PelopsII("data").run();
    }
}
```
## Current Progress

- [X] Tasklist implementation
- [X] Storage Capabilities
- [ ] UI/UX Design

### Notes:
This task management companion is inspired by [Pelops II](https://wikizilla.org/wiki/Pelops_II) from [Godzilla Singular Point](https://godzilla.fandom.com/wiki/Godzilla_Singular_Point). 