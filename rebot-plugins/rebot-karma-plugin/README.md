# ReBot Karma Plugin

The Karma plugin is a very funny way to like or dislike an action or sentence done/sent by an user in a Telegram Group.

It is very simple to use, all you need to do is apped `++` to like or `--` just after a word, username, etc...
It is a very common practice on IRC chats, so why not bring it to Telegram? :)

For example, if you liked a Gif that some user sent, you can increase his karma by appeding `++` to his username:

```
nice gif userX++
```

Then the Plugin will answer:

```
userX has 10 points of karma
```

You can also, query the karma points for a specific user:

```
/karma userX
```

The plugin also allow you to use **like** expressions, for example:

```
/karma %user%
```

Will return all results wich contains the **user** keyword.


### Did you find a bug or do you have a suggestion?
Feel free to raise a [issue](https://github.com/rebasing-xyz/rebot/rebot/issues/new).