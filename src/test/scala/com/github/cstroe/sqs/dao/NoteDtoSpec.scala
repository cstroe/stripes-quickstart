package com.github.cstroe.sqs.dao

import java.time.LocalDateTime

import org.scalatest.FlatSpec

class NoteDtoSpec extends FlatSpec {
  val id = 1
  val author = "Author Name"
  val created = LocalDateTime.now()
  val title = "Note Title"
  val content = "one\ntwo\nthree"
  val group = new NotebookDto(1, "Somegroup")

  "The NoteDto constructor" should "accept valid parameters" in {
    val note = new NoteDto(id, author, created, title, content, group)
    assert(note.getId == id)
    assert(note.getAuthor == author)
    assert(note.getCreated == created)
    assert(note.getTitle == title)
    assert(note.getContent == content)
    assert(note.getNotebook.get == group)
  }

  it should "not accept a negative id" in {
    intercept[IllegalArgumentException] {
      new NoteDto(-1, author, created, title, content, group)
    }
  }

  it should "not accept a null author" in {
    intercept[NullPointerException] {
      new NoteDto(id, null, created, title, content, group)
    }
  }

  it should "not accept a null created date" in {
    intercept[NullPointerException] {
      new NoteDto(id, author, null, title, content, group)
    }
  }

  it should "not accept a null title" in {
    intercept[NullPointerException] {
      new NoteDto(id, author, created, null, content, group)
    }
  }

  it should "not accept null content" in {
    intercept[NullPointerException] {
      new NoteDto(id, author, created, title, null, group)
    }
  }

  it should "accept a null group" in {
    val note = new NoteDto(id, author, created, title, content, null)
    assert(!note.getNotebook.isPresent)
  }
}
