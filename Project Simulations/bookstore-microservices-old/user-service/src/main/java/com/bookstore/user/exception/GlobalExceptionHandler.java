package com.bookstore.user.exception;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {
 @ExceptionHandler(NoSuchElementException.class)
 public ResponseEntity<?> notFound(NoSuchElementException e){
  return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("status",404,"message",e.getMessage()));
 }
 @ExceptionHandler({IllegalArgumentException.class,SecurityException.class})
 public ResponseEntity<?> badRequest(RuntimeException e){
  return ResponseEntity.badRequest().body(Map.of("status",400,"message",e.getMessage()));
 }
 @ExceptionHandler(Exception.class)
 public ResponseEntity<?> internal(Exception e){
  return ResponseEntity.status(500).body(Map.of("status",500,"message","Internal server error"));
 }
}
