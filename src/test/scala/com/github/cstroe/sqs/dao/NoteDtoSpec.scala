package com.github.cstroe.sqs.dao

import java.time.LocalDateTime

import org.scalatest.FlatSpec

class NoteDtoSpec extends FlatSpec {
  val id = 1
  val created = LocalDateTime.now()
  val title = "Note Title"
  val content = "one\ntwo\nthree"
  val notebook = new NotebookDao(1, "Somegroup")
  def noteDao = new NoteDao

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

  it should "not accept null content" in {
    intercept[NullPointerException] {
      new NoteDao(id, created, title, null, notebook)
    }
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

  "The NoteDao created setter" should "not accept a null date" in {
    intercept[NullPointerException] {
      noteDao.setCreated(null)
    }
  }

  "The NoteDao title setter" should "not accept a null title" in {
    intercept[NullPointerException] {
      noteDao.setTitle(null)
    }
  }

  "The NoteDao content setter" should "not accept null content" in {
    intercept[NullPointerException] {
      noteDao.setContent(null)
    }
  }

  "The NoteDao notebook setter" should "not accept a null notebook" in {
    intercept[NullPointerException] {
      noteDao.setNotebook(null)
    }
  }
}
