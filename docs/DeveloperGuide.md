---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* {list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document `docs/diagrams` folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** (consisting of classes [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java)) is in charge of the app launch and shut down.
* At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the app's work is done by the following four components:

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete 1")` API call as an example.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline continues till the end of diagram.
</div>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `AddressBookParser` object which in turn creates a parser that matches the command (e.g., `DeleteCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a person).<br>
   Note that although this is shown as a single step in the diagram above (for simplicity), in the code it can take several interactions (between the command object and the `Model`) to achieve.
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />


The `Model` component,

* stores the address book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>

<img src="images/BetterModelClassDiagram.png" width="450" />

</div>


### Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save both address book data and user preference data in JSON format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.address.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedAddressBook#commit()` — Saves the current address book state in its history.
* `VersionedAddressBook#undo()` — Restores the previous address book state from its history.
* `VersionedAddressBook#redo()` — Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()` and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the initial address book state, and the `currentStatePointer` pointing to that single address book state.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete 5` command to delete the 5th person in the address book. The `delete` command calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add n/David …​` to add a new person. The `add` command also calls `Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</div>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

![UndoRedoState3](images/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index 0, pointing to the initial AddressBook state, then there are no previous AddressBook states to restore. The `undo` command uses `Model#canUndoAddressBook()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</div>

The following sequence diagram shows how an undo operation goes through the `Logic` component:

![UndoSequenceDiagram](images/UndoSequenceDiagram-Logic.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

Similarly, how an undo operation goes through the `Model` component is shown below:

![UndoSequenceDiagram](images/UndoSequenceDiagram-Model.png)

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the address book to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest address book state, then there are no undone AddressBook states to restore. The `redo` command uses `Model#canRedoAddressBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</div>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the address book, such as `list`, will usually not call `Model#commitAddressBook()`, `Model#undoAddressBook()` or `Model#redoAddressBook()`. Thus, the `addressBookStateList` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#commitAddressBook()`. Since the `currentStatePointer` is not pointing at the end of the `addressBookStateList`, all address book states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

![UndoRedoState5](images/UndoRedoState5.png)

The following activity diagram summarizes what happens when a user executes a new command:

<img src="images/CommitActivityDiagram.png" width="250" />

#### Design considerations:

**Aspect: How undo & redo executes:**

* **Alternative 1 (current choice):** Saves the entire address book.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  * Pros: Will use less memory (e.g. for `delete`, just save the person being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.

_{more aspects and alternatives to be added}_

### \[Proposed\] Data archiving

_{Explain here how the data archiving feature will be implemented}_


--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* is a teaching assistant (TA) in higher education
  * manages a significant number of student contacts
  * assists professors in coursework and attendance
    * tutorials
    * assignments


* prefer desktop apps over other types
* is reasonably comfortable using CLI apps
  * prefers typing to mouse interactions
  * can type fast

**Value proposition**: manage TA student contacts & administrative tasks faster than a typical mouse/GUI driven app


### **User Stories**

| Priority | As a … | I want to … | So that I can … | Accepted? |
|----------|--------|-------------|----------------|------------|
| High     | TA     | Save a student's data | Find their details to contact them if needed | No |
| High     | TA     | View a list of all students | See all students I have created | No |
| Medium   | TA     | Search for contacts using name, student ID, or email | Locate students efficiently | No |
| Medium   | TA     | Add notes to each contact | Track important details like consultation requests, special accommodations, and weaknesses | No |
| Medium   | TA     | View the student's data in tabular form | See how the class is doing in general | No |
| Medium   | TA     | Access the contact details of my students | Message them | No |
| High     | TA     | Create tutorial slots | Manage the lesson format for the students | No |
| High     | TA     | Add students to groups | Manage them on a group level | No |
| High     | TA     | Create an assignment entry with a deadline | Track student submissions | No |
| Medium   | TA     | Track whether a student has submitted an assignment | Monitor completion rates | No |
| Medium   | TA     | See submission rates | Assess overall assignment difficulty | No |
| Medium   | TA     | Mark my student as present/unpresent for a specific tutorial slot | Track their attendance | No |
| Medium   | TA     | View the overall performance of my students | Identify if my class is performing well overall | No |
| High     | TA     | Delete tutorial groups | Remove outdated groups | No |
| High     | TA     | Delete a student's data | Make space for the next semester's class | No |

## **Feature List**

| #  | Feature                                        | Priority        | Done        |
|----|-----------------------------------------------|----------------|------------|
| 1  | Add students                                  | MVP            | v1.2       |
| 2  | List all students                            | MVP            | v1.2       |
| 3  | Delete students                              | MVP            | v1.2       |
| 4  | Add Tutorial Slot                            | MVP            | v1.2       |
| 5  | Add students to tutorial group slots        | MVP            | v1.2       |
| 6  | Delete Student from Tutorial Slot           | MVP            | v1.2       |
| 7  | Delete tutorial group slots                 | MVP            | v1.2       |
| 8  | List Tutorial Slots                         | MVP            | v1.2       |
| 9  | Save to file                                  | Nice to have   |  v1.2   |
| 10 | Search for students                           | Nice to have   | Coming soon |
| 11 | Create a lesson under tutorial slot for each student | Nice to have   | Coming soon |
| 12 | Mark lesson attendance                       | Nice to have   | Coming soon |
| 13 | Delete a lesson under tutorial slot for each student | Nice to have   | Coming soon |
| 14 | View overall attendance                      | Nice to have   | Coming soon |
| 15 | Export student contact list                  | Nice to have   | Coming soon |
| 16 | Make announcements to students through email | Nice to have   | Coming soon |
## **Feature Specification**

#### Add Student

**Feature**: Add student  
**Purpose**: To add student contact into the taskbook

**Format**: `add n/NAME p/PHONE_NUMBER e/EMAIL h/HANDLE [t/TUTORIAL_NAME]`

- **NAME** is the name of the student  
  If string is invalid: “The only valid characters are: letters (A-Z, a-z), digits (0-9), underscores (_), hyphens (-), or whitespace ( )”
- **PHONE_NUMBER** is the phone number of the student  
  A valid phone number is defined as having 8 digit number that starts with 6, 8, or 9  
  If PHONE_NUMBER isn’t valid, show error message: “Phone number must have only 8 digits and starts with either 6, 8 or 9”
- **HANDLE** refers to online username of the student (e.g., Telegram or social media platforms)  
  If string is invalid: “The only valid characters are: letters (A-Z, a-z), digits (0-9), underscores (_), hyphens (-), or whitespace ( )”
- **TUTORIAL_NAME** is the name of the tutorial group  
  If tutorial group doesn’t exist: “<TUTORIAL NAME> doesn’t exist”  
  If tutorial group is not a valid string: “The only valid characters are: letters (A-Z, a-z), digits (0-9), underscores (_), hyphens (-)”

**Outputs**:  
- **Success**: Show the newly created student with a success message of “New student added: <STUDENT INFORMATION>”  
- **Incomplete command**: Show error message giving format of command:  
  “Incomplete command. The format is `add n/NAME p/PHONE_NUMBER e/EMAIL h/HANDLE [t/TUTORIAL_NAME]`”

**Duplicate handling**:  
Each student is uniquely identified by their NAME, so duplicate entries are not allowed.

---

#### List All Students

**Feature**: List all students  
**Purpose**: To display a list of all students

**Format**: `list`  
**Parameters**: None

**Outputs**:  
- **Success**: Shows a listing of all the students with success message: “Listed all Students!”

---
#### Delete Student

**Feature**: Delete student  
**Purpose**: Deletes the specified person from the address book

**Format**: `delete INDEX`

- **INDEX** is the index of the user in the current listing  
  If INDEX is not a positive integer: “INDEX must be a positive integer”  
  If INDEX is less than 1 or greater than the number of students listed currently: “Student doesn’t exist”

**Examples**:  
`delete 1`

**Outputs**:  
- **Success**: Show success message: “Delete Student: </STUDENT INFORMATION/>”  
- **Incomplete command**: Show error message giving format of command: “delete INDEX”

**Possible errors**:  
- If the user doesn’t have the student listing view, show error message:  
  “Not in student view; type ‘list’ to list all the students”

---

#### Add Tutorial Slot

**Feature**: Add tutorial slot  
**Purpose**: Add tutorial slots for TA to manage the students

**Format**: `tutorial add NAME`

- **NAME** is the name of the tutorial group  
  If characters are invalid: “The only valid characters are: letters (A-Z, a-z), digits (0-9), underscores (_), hyphens (-)”

**Outputs**:  
- **Success**: Shows the newly added tutorial slot in the user interface: “Successfully added tutorial ‘TUTORIAL_NAME’”  
- **Duplicate**: If a tutorial slot with the same name already exists: “Tutorial slot already exists”  
- **Incomplete command**: Show error message giving format of command: “Incomplete command. The format is `tutorial add NAME`”

**Duplicate handling**:  
Each tutorial slot is uniquely identified by NAME, and duplicate tutorial slots are not allowed.

---

#### Add Student to Tutorial Slot

**Feature**: Add student to tutorial slot  
**Purpose**: Manage students at a group level

**Format**: `tutorial add-student TUTORIAL_NAME [s/INDEX]`

**Examples**:  
`tutorial add-student CS2040S-T58 s/1 s/2 s/3`  
`tutorial add-student CS2040S-T58 s/1`

**Parameters**:  
- **INDEX** refers to the student index in the list  
  If INDEX is not a positive integer: “INDEX must be a positive integer”  
  If INDEX is less than 1 or greater than the number of students listed currently: “Student doesn’t exist”  
- **TUTORIAL_NAME** refers to the tutorial group  
  If characters are invalid: “The only valid characters are: letters (A-Z, a-z), digits (0-9), underscores (_), hyphens (-)”  
  If TUTORIAL_NAME doesn’t exist: “TUTORIAL_NAME doesn’t exist”

**Outputs**:  
- **Success**: Maintain the current listing and update relevant students  
- **Not in student view**: “Not in students view; type ‘list’ to list all the students”  
- **Incomplete command**: Show error message giving format of command:  
  “Incomplete command. The format is `tutorial add-student TUTORIAL_NAME [s/INDEX]`”

---

#### Delete Student from Tutorial Slot

**Feature**: Delete student from tutorial slot  
**Purpose**: Remove the student from tutorial class

**Format**: `tutorial delete-student TUTORIAL_NAME [s/INDEX]`

**Examples**:  
`tutorial delete-student CS2040S-T58 s/1`  
`tutorial delete-student CS2040S-T58 s/1 s/2`

**Parameters**:  
- **INDEX** refers to the student number  
  If INDEX is not a positive integer: “INDEX must be a positive integer”  
  If INDEX is less than 1 or greater than the number of students listed currently: “Student doesn’t exist”

**Outputs**:  
- **Success**: Maintains the current listing with updated tutorial info, and displays “Successfully removed students from ‘TUTORIAL_NAME’”  
- **Student(s) not in tutorial**: “Student(s) not in tutorial”  

---

#### Delete Tutorial Slot

**Feature**: Delete tutorial slot  
**Purpose**: Remove tutorial slots that are no longer in use

**Format**: `tutorial delete TUTORIAL_NAME`

**Examples**:  
`tutorial delete CS2040S-T58`

**Parameters**:  
- **TUTORIAL_NAME** is the name of the tutorial group  
  If characters are invalid: “The only valid characters are: letters (A-Z, a-z), digits (0-9), underscores (_), hyphens (-)”  
  If TUTORIAL_NAME doesn’t exist: “TUTORIAL_NAME doesn’t exist”

**Outputs**:  
- **Success**: Shows success message of “Successfully deleted ‘TUTORIAL_NAME’”  
  The listing view can maintain its current view.

---

#### List Tutorial Slots

**Feature**: List tutorial slots  
**Purpose**: Show all tutorial slots tracked.

**Format**: `tutorial list`

**Parameters**: None

**Outputs**:  
- **Success**: Lists all tutorial slots with the success message: “Showing all tutorial slots.”

---

#### Save to File

**Feature**: Save to file  
**Purpose**: To save all the current student and tutorial data to a file  

**Format**: None

**Outputs**:  None

**Duplicate handling**:  
No duplicates allowed. The file will store the latest data, replacing the previous version.

---

#### Search for Students

**Feature**: Search for students  
**Purpose**: To search for students by their name, phone number, email, or handle  

**Format**: `search <KEYWORD>`  

**Outputs**:  
- **Success**: Displays all students matching the search term with the success message: “Found <NUMBER> students matching ‘<KEYWORD>’”  
- **No results**: Show message: “No students found for ‘<KEYWORD>’”  

**Duplicate handling**: Nil

---

#### Create a Lesson Under Tutorial Slot for Each Student

**Feature**: Create a lesson under tutorial slot for each student  
**Purpose**: To create and assign a lesson to a specific tutorial slot for students  

**Format**: `lesson add TUTORIAL_NAME l/LESSON_NAME d/DATE t/TIME`  

- **TUTORIAL_NAME** refers to the tutorial group  
  If characters are invalid: “The only valid characters are: letters (A-Z, a-z), digits (0-9), underscores (_), hyphens (-)”  
- **LESSON_NAME** refers to the name of the lesson  
  If characters are invalid: “The only valid characters are: letters (A-Z, a-z), digits (0-9), underscores (_), hyphens (-)”  
- **DATE** and **TIME** refer to the lesson's scheduled date and time  

**Outputs**:  
- **Success**: Show success message: “Lesson <LESSON_NAME> successfully added for tutorial <TUTORIAL_NAME>”  
- **Incomplete command**: Show error message: “Incomplete command. The format is `lesson add TUTORIAL_NAME l/LESSON_NAME d/DATE t/TIME`”  

**Duplicate handling**:  
Lessons are unique to each tutorial slot, and duplicates are not allowed.

---

#### Mark Lesson Attendance

**Feature**: Mark lesson attendance  
**Purpose**: To mark attendance for students in a specific lesson under a tutorial slot  

**Format**: `attendance mark TUTORIAL_NAME l/LESSON_NAME s/INDEX`  

- **TUTORIAL_NAME** refers to the tutorial group  
  If characters are invalid: “The only valid characters are: letters (A-Z, a-z), digits (0-9), underscores (_), hyphens (-)”  
- **LESSON_NAME** refers to the lesson name  
- **INDEX** refers to the student's index  


**Outputs**:  
- **Success**: Show success message: “Successfully marked attendance for <STUDENT_NAME> in lesson <LESSON_NAME>”  
- **Incomplete command**: Show error message: “Incomplete command. The format is `attendance mark TUTORIAL_NAME l/LESSON_NAME s/INDEX`”  

**Duplicate handling**:  
Attendance can only be marked once per lesson. If already marked, no changes are made.

---

#### Delete a Lesson Under Tutorial Slot for Each Student

**Feature**: Delete a lesson under tutorial slot for each student  
**Purpose**: To delete a lesson assigned to a tutorial slot  

**Format**: `lesson delete TUTORIAL_NAME l/LESSON_NAME`  

- **TUTORIAL_NAME** refers to the tutorial group  
- **LESSON_NAME** refers to the lesson to be deleted  

**Outputs**:  
- **Success**: Show success message: “Lesson <LESSON_NAME> successfully deleted from tutorial <TUTORIAL_NAME>”  
- **Incomplete command**: Show error message: “Incomplete command. The format is `lesson delete TUTORIAL_NAME l/LESSON_NAME`”  


**Duplicate handling**:  Nil

---

#### View Overall Attendance

**Feature**: View overall attendance  
**Purpose**: To view the overall attendance for all students in the tutorial  

**Format**: `attendance view TUTORIAL_NAME`  

- **TUTORIAL_NAME** refers to the tutorial group  

**Outputs**:  
- **Success**: Show message: “Overall attendance for tutorial <TUTORIAL_NAME>: <ATTENDANCE_PERCENTAGE>% of students attended”  


**Duplicate handling**: Nil

---

#### Export Student Contact List

**Feature**: Export student contact list  
**Purpose**: To export the contact list of all students  

**Format**: `export contacts`  

**Outputs**:  
- **Success**: Show message: “Successfully exported student contact list”  

**Duplicate handling**: Nil
---

#### Make Announcements to Students Through Email

**Feature**: Make announcements to students through email  
**Purpose**: To send announcements to all students through email  

**Format**: `announcement email m/MESSAGE`  

- **MESSAGE** refers to the announcement content  

**Outputs**:  
- **Success**: Show success message: “Announcement sent to all students via email: <MESSAGE>”  
- **Incomplete command**: Show error message: “Incomplete command. The format is `announcement email m/MESSAGE`”  

**Duplicate handling**:  
Emails are sent to all students once.


## **Use cases**

(For all use cases below, the **System** is the `AddressBook` and the **Actor** is the `user`, unless specified otherwise)

#### Use case: Create a student

**Main Success Scenario (MSS)**

1. User sends request to System to create student
2. System creates a new user
3. Use case ends

**Extensions**

- If input is erroneous
  - System displays an error message
  - Use case resumes at step 1

#### Use case: Delete a student

**MSS**

1. User requests to list persons
2. System shows a list of persons
3. User requests to delete a specific person in the list
4. System deletes the person
5. Use case ends.

**Extensions**

- The given index is invalid.
  - System shows an error message.
  - Use case resumes at step 1.

#### Use case: List students

**MSS**

1. User requests to list students
2. System lists all students
3. Use case ends

**Extensions**

- If there are no students
  - System doesn't display anything
  - Use case ends

#### Use case: List tutorial slots

**MSS**

1. User requests to list tutorial slots
2. System lists all tutorial slots
3. Use case ends

**Extensions**

- If there are no tutorial slots
  - System doesn't display anything
  - Use case ends

#### Use case: Create a tutorial slot

**MSS**

1. User requests to create tutorial slot
2. System creates a new tutorial slot
3. Use case ends

**Extensions**

- If tutorial slot already exists
  - System shows an error message
  - Use case continues at step 1
- If tutorial slot name is invalid
  - System shows an error message
  - Use case continues at step 1

#### Use case: Delete a tutorial slot

**MSS**

1. User requests to delete tutorial slot
2. System deletes specified tutorial slot
3. Use case ends

**Extensions**

- If specified tutorial slot doesn't exist
  - System shows an error message
  - Use case ends
- If tutorial slot name is invalid
  - System shows an error message
  - Use case continues at step 1

#### Use case: Add student to tutorial slot

**MSS**

1. User lists all students
2. System lists all students
3. User sends the command to add students to tutorial slots
4. System adds the students to the respective tutorial slots
5. Use case ends

**Extensions**

- If user doesn't list students first,
  - System shows error message
  - Use case continues at step 1
- If user inputs invalid information
  - System shows error message
  - Use case continues at step 1


#### Use case: Delete student from tutorial slot

**MSS**

1. User lists all students
2. System lists all students
3. User sends the command to delete students to tutorial slots
4. System deletes the students to the respective tutorial slots
5. Use case ends

**Extensions**

- If user doesn't exist
  - System shows error message
  - Use case ends
- If user doesn't list students first,
  - System shows error message
  - Use case continues at step 1
- If user inputs invalid information
  - System shows error message
  - Use case continues at step 1

### Non-Functional Requirements

1.  The system should work on any _mainstream OS_ as long as it has Java `17` or above installed.
2.  The system should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4.  The system should respond within 2 seconds.
5.  The system should only be for a single user.
6.  The system's data should be stored locally.
7.  The system should work without requiring an installer.
8.  The system should not require a remote server.
9.  The system's GUI should work well for standard screen resolutions 1920x1080 and higher.
10. The system should be easy to use for a user who has no experience using an address book software.
11. The system should not interfere with other software.
12. The system should save data automatically after each modification in the address book.
13. The system's saved data should still be accessible even if it crashes.
14. The system should not require a database management system for storing data.
15. The system should be able to handle invalid commands without crashing.
16. The system's features should be easy to test.
17. The system's commands should not cause confusion (i.e. not ambiguous).
18. The system should be at most 100MB in size.
19. The system should only require one JAR file.
20. A user who has not used the software for a while should be able to use it efficiently immediately after returning to it.
21. The User Guide and Developer Guide should be PDF-friendly.
22. The system should be able to read a save file provided it is in the correct path and has the correct name.

### Glossary

#### A
* **Address Book**: The system that manages student contacts and administrative tasks for TAs.
* **Assignment Entry**: A record representing an assignment with an associated deadline.
#### C
* **CLI (Command-Line Interface)**: A text-based interface that allows users to interact with the system via commands.
* **Command**: A specific instruction given by the user in the CLI to perform an action.
* **Contact Details**: Information related to a student such as name, student ID, email, and notes.
#### D
* **Deadline**: The due date associated with an assignment entry.
* **Desktop Application**: A software application designed to run on a computer rather than a web browser or mobile device.
#### E
* **Error Message**: A notification displayed when a user inputs invalid or incorrect data.
#### G
* **GUI (Graphical User Interface)**: A visual interface allowing users to interact with the system using a mouse, windows, and icons. Not preferred by the target user.
* **Group**: A collection of students managed together, typically for tutorial purposes.
#### I
* **Invalid Command**: A user-entered command that TAskbook does not recognize or process correctly.
#### L
* **Local Storage**: The method by which TAskbook saves and retrieves data on the user's device rather than using a remote server.
#### M
* **Mainstream OS**: Windows, Linux, Unix, MacOS
#### N
* **Notes**: Additional information that a TA can attach to a student’s record.
#### P
* **Private contact detail**: A contact detail that is not meant to be shared with others
#### S
* **Save File**: A file containing stored student data that TAskbook can read and write to.
* **Student**: An entity in TAskbook representing an individual that a TA manages.
* **Student ID**: A unique identifier assigned to each student by the school / institution.
#### T
* **TA (Teaching Assistant)**: The primary user of TAskbook, responsible for managing student contacts and administrative tasks.
* **Tabular Format**: A structured way of displaying student data in rows and columns.
* **Tutorial Slot**: A scheduled session that students can be assigned to and managed by the TA.

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Deleting a person

1. Deleting a person while all persons are being shown

   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Creating a tutorial slot

1. Creating a tutorial slot
    1. Test case: `tutorial add cs2103-f15`<br/>
        Expected: Shows the list of tutorials.
        Newly added tutorial should be at the bottom of the list.
        Success message indicating tutorial is successfully added.
    1. Test case: `tutorial add cs2103+f15`<br/>
        Expected: Shows an error message indicating that name contains invalid characters.
1. Create a tutorial slot with duplicate name
    1. Prerequisites: Tutorial list should have a tutorial slot with name `cs2103-f15`.
        - If not, u can create it using `tutorial add cs2103-f15`.
    1. Test case: `tutorial add cs2103-f15`<br/>
        Expected: An error messaging indicating that tutorial slot already exists.

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
