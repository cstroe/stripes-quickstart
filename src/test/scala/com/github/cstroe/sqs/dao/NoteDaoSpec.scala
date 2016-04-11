package com.github.cstroe.sqs.dao

import java.time.LocalDateTime

import org.scalatest.FlatSpec

class NoteDaoSpec extends FlatSpec {
  val id = 1
  val created = LocalDateTime.now()
  val title = "Note Title"
  val content = "one\ntwo\nthree"
  val notebook = new NotebookDao(1, "Somegroup")
  val noteDao = new NoteDao

  "The NoteDao constructor" should "accept valid parameters" in {
    val note = new NoteDao(id, created, title, content, notebook)
    assert(note.getId == id)
    assert(note.getCreated == created)
    assert(note.getTitle == title)
    assert(note.getContent == content)
    assert(note.getNotebook == notebook)
  }

  it should "not accept a negative id" in {
    intercept[IllegalArgumentException] {
      new NoteDao(-1, created, title, content, notebook)
    }
  }

  it should "not accept a null created date" in {
    intercept[NullPointerException] {
      new NoteDao(id, null, title, content, notebook)
    }
  }

  it should "not accept a null title" in {
    intercept[NullPointerException] {
      new NoteDao(id, created, null, content, notebook)
    }
  }

  it should "accept null content" in {
    val note = new NoteDao(id, created, title, null, notebook)
    assert(note.getContent == null)
  }

  it should "not accept a null notebook" in {
    intercept[NullPointerException] {
      new NoteDao(id, created, title, content, null)
    }
  }

  "The NoteDao id setter" should "not accept negative ids" in {
    intercept[IllegalArgumentException] {
      noteDao.setId(-1)
    }
  }

  it should "set the id properly" in {
    noteDao.setId(9232)
    assert(noteDao.getId == 9232)
  }

  "The NoteDao created setter" should "not accept a null date" in {
    intercept[NullPointerException] {
      noteDao.setCreated(null)
    }
  }

  it should "set the created date properly" in {
    val date = LocalDateTime.now
    noteDao.setCreated(date)
    assert(noteDao.getCreated.equals(date))
  }

  "The NoteDao title setter" should "not accept a null title" in {
    intercept[NullPointerException] {
      noteDao.setTitle(null)
    }
  }

  it should "set the title properly" in {
    noteDao.setTitle("a title")
    assert(noteDao.getTitle == "a title")
  }

  "The NoteDao content setter" should "accept null content" in {
    noteDao.setContent(null)
    assert(noteDao.getContent == null)
  }

  it should "set the content properly" in {
    noteDao.setContent("some content here")
    assert(noteDao.getContent == "some content here")
  }

  "The NoteDao notebook setter" should "not accept a null notebook" in {
    intercept[NullPointerException] {
      noteDao.setNotebook(null)
    }
  }

  it should "set the notebook properly" in {
    noteDao.setNotebook(notebook)
    assert(noteDao.getNotebook.getId == notebook.getId)
    assert(noteDao.getNotebook.getName == notebook.getName)
  }
}
