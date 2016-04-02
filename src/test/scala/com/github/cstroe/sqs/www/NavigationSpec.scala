package com.github.cstroe.sqs.www

import org.scalatest.{FlatSpec, ShouldMatchers}
import org.scalatest.selenium.HtmlUnit

class NavigationSpec extends FlatSpec with ShouldMatchers with HtmlUnit {
  val host = "http://localhost:8080/"

  "The home page" should "have the correct title and nav button highlighted" in {
    go to host + classOf[NoteActionBean].getSimpleName.replace("ActionBean", "") + ".action"
    pageTitle should be ("View Notes")

    val viewNotes = find("viewNotesNav").get
    viewNotes.tagName should be ("li")
    viewNotes.attribute("class").get should include ("active")

    val addNotebook = find("addNotebookNav").get
    addNotebook.tagName should be ("li")
    addNotebook.attribute("class").get should not include ("active")

    val addNote = find("addNoteNav").get
    addNote.tagName should be ("li")
    addNote.attribute("class").get should not include ("active")
  }

  "The add notebook page" should "have the correct title and nav button highlighted" in {
    go to host + classOf[NotebookActionBean].getSimpleName.replace("ActionBean", "") + ".action?create"

    val viewNotes = find("viewNotesNav").get
    viewNotes.tagName should be ("li")
    viewNotes.attribute("class").get should not include ("active")

    val addNotebook = find("addNotebookNav").get
    addNotebook.tagName should be ("li")
    addNotebook.attribute("class").get should include ("active")

    val addNote = find("addNoteNav").get
    addNote.tagName should be ("li")
    addNote.attribute("class").get should not include ("active")
  }

  "The add note page" should "have the correct title and nav button highlighted" in {
    go to host + classOf[NoteActionBean].getSimpleName.replace("ActionBean", "") + ".action?create"

    val viewNotes = find("viewNotesNav").get
    viewNotes.tagName should be ("li")
    viewNotes.attribute("class").get should not include ("active")

    val addNotebook = find("addNotebookNav").get
    addNotebook.tagName should be ("li")
    addNotebook.attribute("class").get should not include ("active")

    val addNote = find("addNoteNav").get
    addNote.tagName should be ("li")
    addNote.attribute("class").get should include ("active")
  }
}