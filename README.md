
This project was completed for the University of Limerick module *CS5052 - Programming B*.
The course director set a data mining use case, in which the student's program would read in text, in the format of individual emails, and calculate the 10 most common words used by each email address.

In the event that it is of interest, the project's full specifications are outlined below.



**CS5112 and CS5052 Programming Assignment**

Gobbledegook Inc is a multinational company that offers users of its services a free gPost mailbox facility.
However, the mailbox isn’t really free. In return for the ‘free’ service users must agree to allow Gobbledegook
Inc analyse all emails sent and received from or to the gPost mailbox.
Gobbledegook Inc analyses the text of the messages and builds word lists of the words used in the messages.
The most frequently occurring words are then matched with marketing promotions to target users who may
be interested in the products being marketed.
For the purposes of this programming assignment we are going to implement a simulation of the actual
system. A sample of the messages sent to gPost accounts has been stored in a file called
“gPostMessages.txt”. Each message has the format

>**gPostAddress** **gPostBegin** text of message **gPostEnd**

where

* The gPostAddress identifies the mailbox that sent the message.
* The labels gPostBegin and gPostEnd are used for the purposes of bracketing the message
text (i.e. formally identifying the beginning and end of the message text).
* The text of the message. Note the text of the message may be any length and may span
several lines. We will only concern ourselves with message content that we categorise as
“words” (i.e. is wholly alphabetic containing only letters of the alphabet in upper, lower or mixed
case). All other content will be ignored.

A typical message has an entry in the file as follows

>WillyWonka@gPost.com gPostBegin To find the golden ticket you have to buy a bar
of chocolate :) Charlie’s Granny and Grandad are hoping he gets a ticket but he only
has enough money to buy 1 bar. I printed 5 tickets but my Oompa-Loompa workers
made more than 1000000 bars :) gPostEnd

The file “gPostMessages.txt” is processed by reading the message entries and splitting them into the individual
words. Noise words 1 (e.g. me, you, it, this, that, these, etc. - A full list of noise words is available in the file **NoiseWords.txt.**) and other text that is not alphabetic are removed.
The remaining words are stored in a list associated with the email address. Each entry in that list will contain a
word and the number of times that word has occurred in messages sent or received by the user. For example,
the list for *WillyWonka@gPost.com* would be identified by the name *WillyWonka* and might have a list
of entries with a word and a corresponding count like the following
>{WillyWonka -> {chocolate,1000}, {Oompa-Loompa,500}, {golden,300}, {ticket,300}}

When all the messages in the file have been analysed a report is produced listing all the gPost email addresses
and, for each one, the top 10 words used and their frequencies. The email addresses are listed in alphabetic
order case insensitive and for each email address the words are listed in descending frequency order. If two
words have the same frequency they should be list in alphabetic order case insensitive.
