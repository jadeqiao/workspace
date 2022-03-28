# workspace
1.Requirement
Word Processor processes a List of Strings and applies the following business rules:
- start/end with "String"(i.e. "M" or "m")
- longer/shorter than "Length"(i.e. 5) characters

2.Solution
Build a web site, input strings need to be filter and filter rules, return filtered strings.

3.project structure
+ java file: 
- com.example.demo
  ---Application.java----start SpringBoot and configure mvc
- com.example.demo.entity
     ---Rule.java----define filter rule entity, such as start string, min length 
- com.example.demo.service
     ---WordService.java----define service interface
- com.example.demo.service.impl
     ---WordServiceImpl.java----define service implementation
- com.example.demo.web
     ---WordController.java----define controller of the project   
     (1) index function: get method, display input page  
     (2) wordCount function: post method, submit and process the input strings
+ resouce file:
- word.html---display input strings, filter rules and result strings
- base.html---template page
- error.html---error page
