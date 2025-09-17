import 'package:hive_flutter/hive_flutter.dart';

class ToDoDataBase {

  List toDoList = [];

  // reference our box
  final _mybox = Hive.box('mybox');

  // run this method if this is the 1st time ever opening this app
  void createInitialData() {
    toDoList = [
      ['Make Tutorial', false],
      ['Do Exercise', false],
    ];
  }

  // load the data
  void loadData() {
    toDoList = _mybox.get('TODOLIST');
  }

  // update the data
  void updateDataBase() {
    _mybox.put('TODOLIST', toDoList);
  }

}