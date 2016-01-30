# OrderCloudKotlin 

[![CI Status](https://travis-ci.org/feda12/OrderCloudKotlin.svg?branch=master)](https://travis-ci.org/feda12/OrderCloudKotlin)

OrderCloudSwifty is a SDK for Four51's OrderCloud API written in Kotlin and compatible with Java.

## Requirements
- Android API 15 

## Communication

- If you **need help**, use [Stack Overflow](http://stackoverflow.com/questions/tagged/ordercloud). (Tag 'ordercloud')
- If you'd like to **ask a general question**, use [Stack Overflow](http://stackoverflow.com/questions/tagged/ordercloud).
- If you have any questions or need help, join the [#ordercloudevs](http://slack.com/four51/orderclouddevs) channel on [Slack](http://slack.com)
- If you **found a bug**, open an issue.
- If you **have a feature request**, open an issue.

## Installation

### With Gradle

### With Maven

### Manually

1. Clone this repository and cd into it

```bash
$ git clone http://github.com/feda12/OrderCloudKotlin.git
$ cd OrderCloudKotlin
```

2. Install [gradle](http://www.gradle.com)

3. Run the task `generateOrderCloudJar`

```bash
$ gradle generateOrderCloudJar
```

4. You will now have a file called `OrderCloud-all-0.1.0.jar` in `build/lib`

5. Copy this `jar` file to your project directory and include it in the build command

> Alternatively, if you use Gradle in your project but want to build the most recent version, you simply move the `jar` file to the `libs` directory at the root of your project and include it using:

```ogdl
compile fileTree(dir: 'libs', include: ['*.jar'])
```

## Usage

### Setup

Import the OrderCloudKotlin package and setup your client id:

- Kotlin

```kotlin

import com.four51.ordercloud.*

fun main(args: Array<String>) {
  OrderCloud.setupClientId("my-client-id")
}
```

- Java

```java
import com.four51.ordercloud.*

public class Main {
  public void main(String args) {
    OrderCloud.setupClientId("mi-client-id")
  }
}
```

### Authenticating

Once you have set up the client ID, you can now log in using a User account created in the [OrderCloud DevCenter](http://devcenter.ordercloud.io) console.

- Kotlin

```kotlin
User.currentUser.authenticate("username", password = "password")
```

- Java

```java
User.currentUser.authenticate("username", "password")
```

You can also specifoc the scope of the suer as well as the callback to use.

- Kotlin

```kotlin
User.currentUser.authenticate("username", password = "password", scope = "scope", completionHandler = { request, response, result ->
  val (data, error) = result
  if (error == null) {
    obj.deserialize(Util.strToJsonObject(data!!))
  } else {
    println(error)
  }
}
```

- Java

```java
User.currentUser.authenticate("username", password = "password", scope = "scope", completionHandler = { request, response, result ->
  val (data, error) = result
  if (error == null) {
    obj.deserialize(Util.strToJsonObject(data!!))
  } else {
    println(error)
  }
}
```

### Handling request response

Most functions that call the API will give you the possibility to define your own callback. This ensures that your application stays asynchronous, especially in the context of a GUI. To handle the response of the request, default callbacks are provided in the `Util.swift` file. Here is a good structure to use:

- Kotlin

```kotlin

```

- Java 

```java

```

## License

OrderCloudKotlin is available under the MIT license. See the LICENSE file for more info.
