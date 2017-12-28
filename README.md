## Android StyleableToast
![](https://img.shields.io/badge/API-16%2B-brightgreen.svg?style=flat)
[![LICENSE](https://img.shields.io/badge/License-Apache--2.0-brightgreen.svg)](https://github.com/Muddz/StyleableToast/blob/master/LICENSE)
[![APK](https://img.shields.io/badge/APK-Demo-brightgreen.svg)](https://github.com/Muddz/StyleableToast/raw/master/demo.apk)

An Android library that takes the standard Android toast to the next level with many styling options that gives your app and user experience an extra unique feeling! Style your toasts either by code or define styles in styles.xml!


## Update version: 2.0.1  **IMPORTANT** |  18 December 2017

!! READ THE CHANGES FROM `1.0.9` TO `2.0.1` BEFORE UPDATING
- replaced alpha() with solidBackground()
- replaced strokeWidth() and strokeColor() with stroke(width,color)
- Removed getToast() and build()
- Removed 4 lines text limit
- added iconLeft() & iconRight()
- added textSize()
- deprecated typeface(). Use font() with the new Font api in Android 8 -> R.font.xx
- added new custom styles.xml attribute. See below!
- added new makeText() constructor without length parameter
- Adjusted the default paddings and values for the toast
- Over all refactoring

## Cases:

<img src="https://github.com/Muddz/StyleableToast/blob/master/cases.png" width="80%">


## Style a toast from styles.xml

1) Define a styles. All available attributes:
```xml
    <style name="mytoast">
        <item name="textBold">true</item>
        <item name="textColor">#fff</item>
        <item name="textFont">@font/dosier</item>
        <item name="textSize">14sp</item>
        <item name="colorBackground">#fff</item>
        <item name="solidBackground">true</item>
        <item name="strokeWidth">3dp</item>     API 21+
        <item name="strokeColor">#fff</item>    API 21+
        <item name="iconLeft">@drawable/ic</item>
        <item name="iconRight">@drawable/ic</item>
        <item name="length">LONG</item>         ENUM: LONG | SHORT
        <item name="cornerRadius">5dp</item>
    </style>

```

2) Pass your style in the static constructor and call show(); and you're done!

```java
    StyleableToast.makeText(context, "Hello World!", R.style.mytoast).show();
```

## With Builder pattern:
```java
        new StyleableToast
                .Builder(context)
                .text("Hello world!")
                .textColor(Color.WHITE)
                .backgroundColor(Color.BLUE)
                .show();
```

    
## Installation

Add the depedency in your `build.gradle`
```groovy
dependencies {
    implementation 'com.muddzdev:styleabletoast:2.0.1'   
}
```

## License

    Copyright 2017 Muddii Walid (Muddz)

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
