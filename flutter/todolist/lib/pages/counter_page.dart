import 'package:flutter/material.dart';

class CounterPage extends StatefulWidget {
  const CounterPage({Key? key}) : super(key: key);

  @override
  State<CounterPage> createState() => _CounterPageState();
}

class _CounterPageState extends State<CounterPage> {

  // variable
  int  _counter = 0;

  // method
  void _incrementCounter() {
    setState(() {
      _counter++;
    });
  }

  // UI (user interface)


  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            // message
            Text('You pushed the button this many times:'),

            // counter value
            Text(_counter.toString(), style: TextStyle(fontSize: 40)),

            // button
            ElevatedButton(onPressed: _incrementCounter, child: Text('Increment!'))
          ],
        ),
      ),
    );
  }
}

