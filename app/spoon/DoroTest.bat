adb push ../../app/spoon/resource/ /sdcard/
adb reboot
adb wait-for-device
java -jar spoon-runner-1.6.2-jar-with-dependencies.jar ^
--title 1.0.0  ^
--apk ../../app/build/outputs/apk/app-debug.apk ^
--test-apk ../../app/build/outputs/apk/app-debug-androidTest.apk ^
--e listener=ckt.listener.TestFailedListener ^
--e package=doro.testcase ^
--grant-all ^
--debug
