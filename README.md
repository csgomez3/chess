# ♕ BYU CS 240 Chess

This project demonstrates mastery of proper software design, client/server architecture, networking using HTTP and WebSocket, database persistence, unit testing, serialization, and security.

## 10k Architecture Overview

The application implements a multiplayer chess server and a command line chess client.

[![Sequence Diagram](10k-architecture.png)](https://sequencediagram.org/index.html#initialData=C4S2BsFMAIGEAtIGckCh0AcCGAnUBjEbAO2DnBElIEZVs8RCSzYKrgAmO3AorU6AGVIOAG4jUAEyzAsAIyxIYAERnzFkdKgrFIuaKlaUa0ALQA+ISPE4AXNABWAexDFoAcywBbTcLEizS1VZBSVbbVc9HGgnADNYiN19QzZSDkCrfztHFzdPH1Q-Gwzg9TDEqJj4iuSjdmoMopF7LywAaxgvJ3FC6wCLaFLQyHCdSriEseSm6NMBurT7AFcMaWAYOSdcSRTjTka+7NaO6C6emZK1YdHI-Qma6N6ss3nU4Gpl1ZkNrZwdhfeByy9hwyBA7mIT2KAyGGhuSWi9wuc0sAI49nyMG6ElQQA)

## Modules

The application has three modules.

- **Client**: The command line program used to play a game of chess over the network.
- **Server**: The command line program that listens for network requests from the client and manages users and games.
- **Shared**: Code that is used by both the client and the server. This includes the rules of chess and tracking the state of a game.

## Starter Code

As you create your chess application you will move through specific phases of development. This starts with implementing the moves of chess and finishes with sending game moves over the network between your client and server. You will start each phase by copying course provided [starter-code](starter-code/) for that phase into the source code of the project. Do not copy a phases' starter code before you are ready to begin work on that phase.

## IntelliJ Support

Open the project directory in IntelliJ in order to develop, run, and debug your code using an IDE.

## Maven Support

You can use the following commands to build, test, package, and run your code.

| Command                    | Description                                     |
| -------------------------- | ----------------------------------------------- |
| `mvn compile`              | Builds the code                                 |
| `mvn package`              | Run the tests and build an Uber jar file        |
| `mvn package -DskipTests`  | Build an Uber jar file                          |
| `mvn install`              | Installs the packages into the local repository |
| `mvn test`                 | Run all the tests                               |
| `mvn -pl shared test`      | Run all the shared tests                        |
| `mvn -pl client exec:java` | Build and run the client `Main`                 |
| `mvn -pl server exec:java` | Build and run the server `Main`                 |

These commands are configured by the `pom.xml` (Project Object Model) files. There is a POM file in the root of the project, and one in each of the modules. The root POM defines any global dependencies and references the module POM files.

## Running the program using Java

Once you have compiled your project into an uber jar, you can execute it with the following command.

## Phase 2

The following link is for the server design sequence diagram created for phase 2: https://sequencediagram.org/index.html?presentationMode=readOnly#initialData=IYYwLg9gTgBAwgGwJYFMB2YBQAHYUxIhK4YwDKKUAbpTngUSWDABLBoAmCtu+hx7ZhWqEUdPo0EwAIsDDAAgiBAoAzqswc5wAEbBVKGBx2ZM6MFACeq3ETQBzGAAYAdAE5M9qBACu2AMQALADMABwATG4gMP7I9gAWYDoIPoYASij2SKoWckgQaJiIqKQAtAB85JQ0UABcMADaAAoA8mQAKgC6MAD0PgZQADpoAN4ARP2UaMAAtihjtWMwYwA0y7jqAO7QHAtLq8soM8BICHvLAL6YwjUwFazsXJT145NQ03PnB2MbqttQu0WyzWYyOJzOQLGVzYnG4sHuN1E9SgmWyYEoAAoMlkcpQMgBHVI5ACU12qojulVk8iUKnU9XsKDAAFUBhi3h8UKTqYplGpVJSjDpagAxJCcGCsyg8mA6SwwDmzMQ6FHAADWkoGME2SDA8QVA05MGACFVHHlKAAHmiNDzafy7gjySp6lKoDyySIVI7KjdnjAFKaUMBze11egAKKWlTYAgFT23Ur3YrmeqBJzBYbjObqYCMhbLCNQbx1A1TJXGoMh+XyNXoKFmTiYO189Q+qpelD1NA+BAIBMU+4tumqWogVXot3sgY87nae1t+7GWoKDgcTXS7QD71D+et0fj4PohQ+PUY4Cn+Kz5t7keC5er9cnvUexE7+4wp6l7FovFqXtYJ+cLtn6pavIaSpLPU+wgheertBAdZoFByyXAmlDtimGD1OEThOFmEwQZ8MDQcCyxwfECFISh+xXOgHCmF4vgBNA7CMjEIpwBG0hwAoMAADIQFkhRYcwTrUP6zRtF0vQGOo+RoFmipzGsvz-BwVygYKQH+uB5afJCIJqTsXzQo8wHiVQSIwAgQnihignCQSRJgKSb6GLuNL7gyTJTspXI3l5d5LsKMBihKboynKZbvEqmAqsGGoAHIQDFMAAGa+E2w4OkmvrOl2MA9n224eZZ-opW6IpZRwUYxnGhRaXl8DIKmMDpvhoxjDmqh5vM0EAEIhjAzlqGA3apW82q6vqepyDFRqZT4nANvRgW8sFlnWVVNWlYKOX0jAVAmkgHBNPofw7NOm7yHOQW5ZUy4wAA4kyMDGQCxrqBARByCg646nqC1xQl6owAAkmgIDQCi4DvRd6nrQuArNe59QAOreA451bDse2o9U-qQ9DxYoOAOOXQCdUoLGCnofCyatdh7VOAAjARPV9QWYzMtMl7QEgABe-31OKJOw8wH27HRTbuftt78vUh5-c+8Tnpe14HSjj2hY+AYa1ucvNTp36orisAZKoAGYCbIGE2BhH6f1MFjBRVH1pCmn25hTPjTAuGdXpsUGS7buIR7tGNgxnjeH4-heCg6AxHEiQJ0njm+FgomCqB9QNNIEb8RG7QRt0PRyaoCnDGHSH09p5n+mMYw2w38L5Z2PlgKrDlCZno0kvjVIK4djJd5e6vweHaB3RtD1CvUr3MKrHqgxqvMUQLwvrrZ9iZ0j+52x3RW9v2Rsfq33Yny3sIYQTEmlmMgzN01jMlH76bs11nP5osRYlvUy0N5QCFv9VaTYTRLwNvIGAVobT702u3KyLp9YvkNgVdsJt6jLy3LbLayCOAoG4Mece2DbqDxkMPUcRhCFMhQN3UhwAZ7I3vKFaQND0QwD1IYBh+Nz431LFQCAp1r5fnrvw+omwUA6DID9OsYAXDHDrClbIKAMSchWKoGqpJcHlQfk-OuzVRI4TwlmGWDE1ox2Yv4FE65-DYHFBqfiaIXpKg0NnXRednrFzLvYJU1dLzu0at7PhX5oLN1wYg6yo9u413QG5dBnlZ4jyZDEgJU8mEHxCgvN6DCvqaN+uiAGM1gZzHiqqNefM9Sb3+vA3KeDCrFVPgkkJcJL4lR0Ygxu+iX6VCMSzT+2Z+Rc1-sWaAADKnxGqdLKOZgEAGBQVebQMDrQ5FtJQw+SDCo8LPpUTBCyPQRI7Js+oyAcjPVcRieJnZ5b3UOqcsA5ycyXNqYuHW2TmD3JgBAdKMBfE5gyt4GYLicwvJRvUheSonE5F4bsi+wKUBQsAq3DZukxh-LUAWBo4x0Xg2kAWVm4RgiBBBJseIuoUBuk5F8FYwwxjJFAGqSlkFDJoqVElZltFOhe3vj7N+xjA6spzJi7FSpcX4sJcS5YpLyVMpDsCWl9KQCMqIs7YEgqUDsrlVCLlMzLFxw4AAdjcE4FATgYgRmCHALiAA2eAE5DCPMMEUX2Od7Z51aB0HxfiJmBKzOizVKBuWJlheImAIq5gBtMiIiykTkHRPHrE6e5Ctad1SZPJCGTgpvJejkqBwA8k-SQH9IpQN-JlMSpKCZUzQUbOso0mFDxQ31o6Ucrpz9gmvzah-DmQyf5jD-mMhUVbgFbzAeYuZ3C83LLgVrWtyDtnNJDaE-ZODkXgpgMrdEjqMSOuvEbIetyqGj23ZmueT0IxoCtiiIwfhkAgD+r8isHAIBqCKhAZgsDoWrxrXfY58LXyLtbaWKMaJxT2EddTWm8YektT5SzAV39nYDtLHodcKJCRjTHT+9d9b90UMPUre1270Wa3WVkgMa5-1oOucbOFjq4ATgUpba2Lbc5hvVWK+oBKiVBtvp25mAcCI4rxbUbjgQzGMVjgESwhDbKbGTkgBIYAZN9ggPJgAUkItA8KYiKrVM6t+rr77uuZDJHo6L-HpvQFmbACBgAyagHACAtkoBrGE7xtujbl3jFs-ZygTmXPUuE1xiVZl+FGb-fGs8iarmbJuUko9KSE1pIzT+7Ni8V3QIuoW4t01S0qvLWDde-MR01Nnb+utV8dledacfdpa6PHLG6R23pvs0xsx7bmPtyHxlAJAdMtahD5m5M-WANZh651bLzQ2vZPDDlo1+Uybdjr+6uWTZQzuy3RXSFPa8+eMAL1XsMOi6dqzCvJVSidpa2UyM4aq4BtjKVHXVWWrVaMNMGoGP4+-ExX9e1dj2ENdcq3uZoEuxWQGs14jzVxRlGqWGhtiHmwVeoAArLT27fMOYC9AWLg4D0Jc7k0Oz2PnPQAxFj-zZOoC7e1vtw7Phr1cPeiTygAByAU0MXMwGyMaY6pxdDcHOzAYnfnYA49gLWdA2HY2FXo9TjQ1W2MS7DEhSDn2YN9PTJmP7XWkOjNLCaM0NZwzIQk+Vu7JVqt7ObWu2X9QLqKbQBLinrPHPU7xzuAnyMHfyXsM76nruxcS9pywrBfvtPM8p5L+IhgufQB55H2Pj7SnW7hRLhQEfmMICReF39bavutbg4J0YEm9UBC8PZhTSnK-ykQMGWAwBsC2cIHkAoMADPmAi5JAuRcS5l2MAYpdtWm7Rtvvbjd3A8AKGb88vDKbJ8N5n9gZ55Xs1sKIYYE0CAYqqDc64tYjxjQpfQIrwDezBHCNY26pr7aeWGLa-7X7IwJNAA

NOTE TO SELF: if you need to edit the diagram at all, use this link: https://sequencediagram.org/index.html#initialData=IYYwLg9gTgBAwgGwJYFMB2YBQAHYUxIhK4YwDKKUAbpTngUSWDABLBoAmCtu+hx7ZhWqEUdPo0EwAIsDDAAgiBAoAzqswc5wAEbBVKGBx2ZM6MFACeq3ETQBzGAAYAdAE5M9qBACu2AMQALADMABwATG4gMP7I9gAWYDoIPoYASij2SKoWckgQaJiIqKQAtAB85JQ0UABcMADaAAoA8mQAKgC6MAD0PgZQADpoAN4ARP2UaMAAtihjtWMwYwA0y7jqAO7QHAtLq8soM8BICHvLAL6YwjUwFazsXJT145NQ03PnB2MbqttQu0WyzWYyOJzOQLGVzYnG4sHuN1E9SgmWyYEoAAoMlkcpQMgBHVI5ACU12qojulVk8iUKnU9XsKDAAFUBhi3h8UKTqYplGpVJSjDpagAxJCcGCsyg8mA6SwwDmzMQ6FHAADWkoGME2SDA8QVA05MGACFVHHlKAAHmiNDzafy7gjySp6lKoDyySIVI7KjdnjAFKaUMBze11egAKKWlTYAgFT23Ur3YrmeqBJzBYbjObqYCMhbLCNQbx1A1TJXGoMh+XyNXoKFmTiYO189Q+qpelD1NA+BAIBMU+4tumqWogVXot3sgY87nae1t+7GWoKDgcTXS7QD71D+et0fj4PohQ+PUY4Cn+Kz5t7keC5er9cnvUexE7+4wp6l7FovFqXtYJ+cLtn6pavIaSpLPU+wgheertBAdZoFByyXAmlDtimGD1OEThOFmEwQZ8MDQcCyxwfECFISh+xXOgHCmF4vgBNA7CMjEIpwBG0hwAoMAADIQFkhRYcwTrUP6zRtF0vQGOo+RoFmipzGsvz-BwVygYKQH+uB5afJCIJqTsXzQo8wHiVQSIwAgQnihignCQSRJgKSb6GLuNL7gyTJTspXI3l5d5LsKMBihKboynKZbvEqmAqsGGoAHIQDFMAAGa+E2w4OkmvrOl2MA9n224eZZ-opW6IpZRwUYxnGhRaXl8DIKmMDpvhoxjDmqh5vM0EAEIhjAzlqGA3apW82q6vqepyDFRqZT4nANvRgW8sFlnWVVNWlYKOX0jAVAmkgHBNPofw7NOm7yHOQW5ZUy4wAA4kyMDGQCxrqBARByCg646nqC1xQl6owAAkmgIDQCi4DvRd6nrQuArNe59QAOreA451bDse2o9U-qQ9DxYoOAOOXQCdUoLGCnofCyatdh7VOAAjARPV9QWYzMtMl7QEgABe-31OKJOw8wH27HRTbuftt78vUh5-c+8Tnpe14HSjj2hY+AYa1ucvNTp36orisAZKoAGYCbIGE2BhH6f1MFjBRVH1pCmn25hTPjTAuGdXpsUGS7buIR7tGNgxnjeH4-heCg6AxHEiQJ0njm+FgomCqB9QNNIEb8RG7QRt0PRyaoCnDGHSH09p5n+mMYw2w38L5Z2PlgKrDlCZno0kvjVIK4djJd5e6vweHaB3RtD1CvUr3MKrHqgxqvMUQLwvrrZ9iZ0j+52x3RW9v2Rsfq33Yny3sIYQTEmlmMgzN01jMlH76bs11nP5osRYlvUy0N5QCFv9VaTYTRLwNvIGAVobT702u3KyLp9YvkNgVdsJt6jLy3LbLayCOAoG4Mece2DbqDxkMPUcRhCFMhQN3UhwAZ7I3vKFaQND0QwD1IYBh+Nz431LFQCAp1r5fnrvw+omwUA6DID9OsYAXDHDrClbIKAMSchWKoGqpJcHlQfk-OuzVRI4TwlmGWDE1ox2Yv4FE65-DYHFBqfiaIXpKg0NnXRednrFzLvYJU1dLzu0at7PhX5oLN1wYg6yo9u413QG5dBnlZ4jyZDEgJU8mEHxCgvN6DCvqaN+uiAGM1gZzHiqqNefM9Sb3+vA3KeDCrFVPgkkJcJL4lR0Ygxu+iX6VCMSzT+2Z+Rc1-sWaAADKnxGqdLKOZgEAGBQVebQMDrQ5FtJQw+SDCo8LPpUTBCyPQRI7Js+oyAcjPVcRieJnZ5b3UOqcsA5ycyXNqYuHW2TmD3JgBAdKMBfE5gyt4GYLicwvJRvUheSonE5F4bsi+wKUBQsAq3DZukxh-LUAWBo4x0Xg2kAWVm4RgiBBBJseIuoUBuk5F8FYwwxjJFAGqSlkFDJoqVElZltFOhe3vj7N+xjA6spzJi7FSpcX4sJcS5YpLyVMpDsCWl9KQCMqIs7YEgqUDsrlVCLlMzLFxw4AAdjcE4FATgYgRmCHALiAA2eAE5DCPMMEUX2Od7Z51aB0HxfiJmBKzOizVKBuWJlheImAIq5gBtMiIiykTkHRPHrE6e5Ctad1SZPJCGTgpvJejkqBwA8k-SQH9IpQN-JlMSpKCZUzQUbOso0mFDxQ31o6Ucrpz9gmvzah-DmQyf5jD-mMhUVbgFbzAeYuZ3C83LLgVrWtyDtnNJDaE-ZODkXgpgMrdEjqMSOuvEbIetyqGj23ZmueT0IxoCtiiIwfhkAgD+r8isHAIBqCKhAZgsDoWrxrXfY58LXyLtbaWKMaJxT2EddTWm8YektT5SzAV39nYDtLHodcKJCRjTHT+9d9b90UMPUre1270Wa3WVkgMa5-1oOucbOFjq4ATgUpba2Lbc5hvVWK+oBKiVBtvp25mAcCI4rxbUbjgQzGMVjgESwhDbKbGTkgBIYAZN9ggPJgAUkItA8KYiKrVM6t+rr77uuZDJHo6L-HpvQFmbACBgAyagHACAtkoBrGE7xtujbl3jFs-ZygTmXPUuE1xiVZl+FGb-fGs8iarmbJuUko9KSE1pIzT+7Ni8V3QIuoW4t01S0qvLWDde-MR01Nnb+utV8dledacfdpa6PHLG6R23pvs0xsx7bmPtyHxlAJAdMtahD5m5M-WANZh651bLzQ2vZPDDlo1+Uybdjr+6uWTZQzuy3RXSFPa8+eMAL1XsMOi6dqzCvJVSidpa2UyM4aq4BtjKVHXVWWrVaMNMGoGP4+-ExX9e1dj2ENdcq3uZoEuxWQGs14jzVxRlGqWGhtiHmwVeoAArLT27fMOYC9AWLg4D0Jc7k0Oz2PnPQAxFj-zZOoC7e1vtw7Phr1cPeiTygAByAU0MXMwGyMaY6pxdDcHOzAYnfnYA49gLWdA2HY2FXo9TjQ1W2MS7DEhSDn2YN9PTJmP7XWkOjNLCaM0NZwzIQk+Vu7JVqt7ObWu2X9QLqKbQBLinrPHPU7xzuAnyMHfyXsM76nruxcS9pywrBfvtPM8p5L+IhgufQB55H2Pj7SnW7hRLhQEfmMICReF39bavutbg4J0YEm9UBC8PZhTSnK-ykQMGWAwBsC2cIHkAoMADPmAi5JAuRcS5l2MAYpdtWm7Rtvvbjd3A8AKGb88vDKbJ8N5n9gZ55Xs1sKIYYE0CAYqqDc64tYjxjQpfQIrwDezBHCNY26pr7aeWGLa-7X7IwJNAA

```sh
java -jar client/target/client-jar-with-dependencies.jar

♕ 240 Chess Client: chess.ChessPiece@7852e922
```
