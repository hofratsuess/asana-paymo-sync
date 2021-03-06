# asana-paymo-sync

A Clojure script that syncs projects, sections, tasks and user-task associations coming from [Asana](https://asana.com) with the time tracking service [Paymo](http://www.paymoapp.com/).

[![Build Status](https://travis-ci.org/hofratsuess/asana-paymo-sync.svg?branch=master)](https://travis-ci.org/hofratsuess/asana-paymo-sync)

## How does it work?

As said above, this is a script that will run on a regular interval. It will then make sure that all the information from Asana matches up with Paymo, meaning Paymo is only used to track time while commenting, changing names, due-dates will remain in Asana. Basically it's a 1-way sync.

If your use-case would require a 2-way sync, let us know by opening an issue or creating a pull request.

## Setup

In order for the script to be able to synchronize it needs project and user mappings in `config.clj`. Since they're both `id` based there are some CLI functions that help with filling those out.

```bash
# return projects with their name and id.
lein run asana projects

# return users with their email and id.
lein run asana users

# the same applies for paymo.
lein run paymo projects
lein run paymo users
```

## Development

Make sure that the [heroku toolbelt](https://toolbelt.heroku.com/), [homebrew](http://brew.sh/) and [Leiningen](http://leiningen.org/) (>= 2.5) is installed.

```bash
# install dynamodb local (stable 2014-10-07)
brew update
brew install dynamodb-local

# fetch the environment variables from heroku, which will put them in the .env file
# redact the file so the development machine isn't using the production database
heroku config:pull

# start up the leiningen repl with the environment variables defined in the .env file
foreman run lein repl
```

## License

Copyright © 2015 Hofrat + Suess GmbH

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
