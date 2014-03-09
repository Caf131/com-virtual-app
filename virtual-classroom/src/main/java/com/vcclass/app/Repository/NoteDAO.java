package com.vcclass.app.Repository;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.vcclass.app.Data.Note;

@Repository
public interface NoteDAO 
{
	public void setDataSource(DataSource ds); 
	public List<Note> GetStudentNotes(int studentId); 
	public int AddNote(int studentId, Note note); 
	public boolean DeleteNote(int studentId, int noteId); 
	public boolean UpdateNote(int studentId, int noteId); 
	public Note GetNote(int studentId); 
	//public void AddNoteElement(int studentId, Note note); 
	//public NoteElement GetNoteElementById(int noteId, int noteElementId); 
	//public List<NoteElement> GetNoteElements(Note note);
}
