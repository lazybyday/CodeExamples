package com.DamnItJenkins.ClockworkCrisisPro.Phases.game;

import com.DamnItJenkins.ClockworkCrisisPro.dataModel.ClockConstants;

public class VictoryQuotes
{
	private String[][] m_Quotes = null;

	public static final int QUOTE_AUTHOR = 0;
	public static final int QUOTE_TEXT = 1;
	public static final int QUOTE_CATEGORY = 2;

	private static final int MAX_NUMBER_OF_CHARACTERS = 200;

	
	public String[] getRandomQuote()
	{
		
		int intQuoteIndex = 0;
		while (true)
		{
			intQuoteIndex = ClockConstants.GloablRandom.nextInt(m_Quotes.length);
		
			if (m_Quotes[intQuoteIndex][QUOTE_TEXT].length() <= MAX_NUMBER_OF_CHARACTERS)
			{
				break;	
			}				
		}		
		
		return new String[] {
				m_Quotes[intQuoteIndex][QUOTE_AUTHOR],
				m_Quotes[intQuoteIndex][QUOTE_TEXT],
				m_Quotes[intQuoteIndex][QUOTE_CATEGORY] };
	}

	public VictoryQuotes()
	{

		m_Quotes = new String[][] {
				{ "Friedrich Nietzsche", "Without music, life would be a mistake.", "Music" },
				{
						"Friedrich Nietzsche",
						"Whoever despises himself still respects himself as one who despises.",
						"Miscellaneous" },
				{
						"Friedrich Nietzsche",
						"What is more harmful than any vice? Active pity for all the failures and all the weak: Christianity.",
						"Christianity" },
				{
						"Friedrich Nietzsche",
						"What is happiness? The feeling that power is growing, that resistance is overcome.",
						"Happiness, Power" },
				{
						"Friedrich Nietzsche",
						"What is good? Everything that heightens the feeling of power in man, the will to power, power itself.",
						"Morality, Power" },
				{
						"Friedrich Nietzsche",
						"What is bad? Everything that is born of weakness.",
						"Morality" },
				{
						"Friedrich Nietzsche",
						"There are no moral phenomena at all, but only a moral interpretation of phenomena.",
						"Morality" },
				{ "Friedrich Nietzsche", "There are no facts, only interpretations.", "Facts" },
				{ "Friedrich Nietzsche", "Plato was a bore.", "Miscellaneous" },
				{
						"Friedrich Nietzsche",
						"It is not the lack of love, but a lack of friendship that makes unhappy marriages.",
						"Marriage" },
				{
						"Friedrich Nietzsche",
						"Is man one of God's blunders? Or is God one of man's blunders?",
						"Anti-God" },
				{
						"Friedrich Nietzsche",
						"In Heaven all the interesting people are missing.",
						"Anti-Religion" },
				{
						"Friedrich Nietzsche",
						"In Christianity neither morality nor religion come into contact with reality at any point.",
						"Christianity" },
				{
						"Friedrich Nietzsche",
						"I cannot believe in a God who wants to be praised all the time.",
						"Anti-God" },
				{ "Friedrich Nietzsche", "God is dead.", "Anti-God" },
				{
						"Friedrich Nietzsche",
						"Gaze long into the abyss, and the abyss gazes into you.",
						"Miscellaneous" },
				{
						"Friedrich Nietzsche",
						"Everything done in weakness fails. Moral: Do nothing.",
						"Miscellaneous" },
				{
						"Friedrich Nietzsche",
						"Errors of haste are seldom committed singly. The first time a man always does too much. And precisely on that account he commits a second error, and then he does too little.",
						"Miscellaneous" },
				{
						"Friedrich Nietzsche",
						"Convictions are more dangerous enemies of truth than lies.",
						"Truth & Lies" },
				{
						"Friedrich Nietzsche",
						"Blessed are the forgetful: for they get the better even of their blunders.",
						"Miscellaneous" },
				{
						"Friedrich Nietzsche",
						"At times one remains faithful to a cause only because its opponents do not cease to be insipid.",
						"Miscellaneous" },
				{
						"Friedrich Nietzsche",
						"And those who were seen dancing were thought to be insane by those who could not hear the music.",
						"Miscellaneous" },
				{
						"Friedrich Nietzsche",
						"A very popular error: having the courage of one's convictions; rather it is a matter of having the courage for an attack on one's convictions.",
						"Courage" },
				{
						"Friedrich Nietzsche",
						"A subject for a great poet would be God's boredom after the seventh day of Creation.",
						"God, Poetry" },
				{
						"Friedrich Nietzsche",
						"A few hours of mountain climbing turn a villain and a saint into two rather equal creature. Exhaustion is the shortest way to equality and fraternity, and liberty is added eventually by sleep.",
						"Sleep" },
				{
						"Bertrand Russell",
						"What a man believes upon grossly insufficient evidence is an index into his desires -- desires of which he himself is often unconscious. If a man is offered a fact which goes against his instincts, he will scrutinize it closely, and unless the evidence is overwhelming, he will refuse to believe it. If, on the other hand, he is offered something which affords a reason for acting in accordance to his instincts, he will accept it even on the slightest evidence. The origin of myths is explained in this way.",
						"Anti-Religion" },
				{
						"Bertrand Russell",
						"We are faced with the paradoxical fact that education has become one of the chief obstacles to intelligence and freedom of thought.",
						"Education" },
				{
						"Bertrand Russell",
						"War does not determine who is right - only who is left.",
						"War & Peace" },
				{
						"Bertrand Russell",
						"To teach how to live with uncertainty, yet without being paralyzed by hesitation, is perhaps the chief thing that philosophy can do.",
						"Philosophy" },
				{ "Bertrand Russell", "To conquer fear is the beginning of wisdom.", "Fear, Wisdom" },
				{
						"Bertrand Russell",
						"Those who forget good and evil and seek only to know the facts are more likely to achieve good than those who view the world through the distorting medium of their own desires.",
						"Good & Evil, Morality" },
				{ "Bertrand Russell", "Those who fear life are already three parts dead.", "Fear" },
				{
						"Bertrand Russell",
						"The puritanism of Christianity has played havoc with the moderation that an enlightened and tolerant critical spirit would have produced. I've noticed that in whatever country, county, town, or other region there is a regulation enjoining temperance, the population seems to be entirely composed of teetotallers and drunkards. There's a Bible on that shelf there. But I keep it next to Voltaire - poison and antidote.",
						"Christianity" },
				{
						"Bertrand Russell",
						"The point of philosophy is to start with something so simple as not to seem worth stating, and to end with something so paradoxical that no one will believe it.",
						"Philosophy" },
				{
						"Bertrand Russell",
						"The degree of one's emotions varies inversely with one's knowledge of the facts: the less you know the hotter you get.",
						"Anger, Facts" },
				{
						"Bertrand Russell",
						"So far as I can remember, there is not one word in the Gospels in praise of intelligence.",
						"Christianity" },
				{
						"Bertrand Russell",
						"Philosophers, for the most part, are constitutionally timid, and dislike the unexpected. Few of them would be genuinely happy as pirates or burglars. Accordingly they invent systems which make the future calculable, at least in its main outlines.",
						"Philosophy" },
				{
						"Bertrand Russell",
						"No one gossips about other people's secret virtues.",
						"Talking, Virtue" },
				{
						"Bertrand Russell",
						"Neither a man nor a crowd nor a nation can be trusted to act humanely or to think sanely under the influence of a great fear.",
						"Fear" },
				{
						"Bertrand Russell",
						"Men are born ignorant, not stupid; they are made stupid by education.",
						"Education" },
				{
						"Bertrand Russell",
						"Many people would sooner die than think. In fact they do.",
						"Thinking" },
				{
						"Bertrand Russell",
						"Man needs, for his happiness, not only the enjoyment of this or that, but hope and enterprise and change.",
						"Happiness" },
				{ "Bertrand Russell", "Love is, above all, the gift of oneself.", "Love" },
				{
						"Bertrand Russell",
						"Love is something far more than desire for sexual intercourse; it is the principal means of escape from the loneliness which afflicts most men and women throughout the greater part of their lives.",
						"Love" },
				{
						"Bertrand Russell",
						"It is preoccupation with possessions, more than anything else, that prevents us from living freely and nobly.",
						"Money" },
				{
						"Marcus Tullius Cicero",
						"Wise men are instructed by reason; men of less understanding, by experience; the most ignorant, by necessity; the beasts, by nature.",
						"Wisdom" },
				{
						"Marcus Tullius Cicero",
						"Times are bad. Children no longer obey their parents, and everyone is writing a book.",
						"Writing" },
				{
						"Marcus Tullius Cicero",
						"There is nothing so absurd but some philosopher has said it.",
						"Philosophy" },
				{ "Marcus Tullius Cicero", "No sane man will dance.", "Dancing" },
				{
						"Marcus Tullius Cicero",
						"In the very books in which philosophers bid us scorn fame, they inscribe their names.",
						"Philosophy, Writing" },
				{
						"Marcus Tullius Cicero",
						"An unjust peace is better than a just war.",
						"War & Peace" },
				{ "Marcus Tullius Cicero", "All splendid things are rare.", "Miscellaneous" },
				{
						"Jean-Jacques Rousseau",
						"What wisdom can you find that is greater than kindness?",
						"Virtue, Wisdom" },
				{
						"Jean-Jacques Rousseau",
						"To write a good love letter, you ought to begin without knowing what you mean to say and to finish without knowing what you have written.",
						"Love, Writing" },
				{
						"Jean-Jacques Rousseau",
						"It is in man's heart that the life of nature's spectacle exists; to see it, one must feel it.",
						"Nature" },
				{
						"Plato",
						"You can discover more about a person in an hour of play than in a year of conversation.",
						"Talking" },
				{
						"Plato",
						"You are young, my son, and, as the years go by, time will change and even reverse many of your present opinions. Refrain therefore awhile from setting yourself up as a judge of the highest matters.",
						"Youth" },
				{
						"Plato",
						"Wise men talk because they have something to say; fools, because they have to say something.",
						"Talking" },
				{
						"Plato",
						"Wealth is the parent of luxury and indolence, and poverty of meanness and viciousness, and both of discontent.",
						"Money" },
				{
						"Plato",
						"There are three arts which are concerned with all things: one which uses, another which makes, and a third which imitates them.",
						"Art" },
				{ "Plato", "The soul of man is immortal and imperishable.", "God" },
				{
						"Plato",
						"The price good men pay for indifference to public affairs is to be ruled by evil men.",
						"Good & Evil, Politics" },
				{
						"Plato",
						"The partisan, when he is engaged in a dispute, cares nothing about the rights of the question, but is anxious only to convince his hearers of his own assertions.",
						"Debate" },
				{
						"Plato",
						"The hour of departure has arrived, and we go our ways - I to die, and you to live. Which is better God only knows.",
						"Life & Death" },
				{ "Plato", "The greatest wealth is to live content with little.", "Money" },
				{
						"Plato",
						"The greatest penalty of evildoing - namely, to grow into the likeness of bad men.",
						"Good & Evil" },
				{
						"Plato",
						"The direction in which education starts a man will determine his future life.",
						"Education" },
				{ "Plato", "The beginning is the most important part of the work.", "Work" },
				{ "Plato", "No human thing is of serious importance.", "Miscellaneous" },
				{
						"Plato",
						"No evil can happen to a good man, either in life or after death.",
						"Good & Evil" },
				{
						"Plato",
						"Never discourage anyone who continually makes progress, no matter how slow.",
						"Success" },
				{
						"Plato",
						"Must not all things at the last be swallowed up in death?",
						"Life & Death" },
				{
						"Plato",
						"Mankind censure injustice fearing that they may be the victims of it, and not because they shrink from committing it.",
						"Justice" },
				{
						"Plato",
						"Laws are partly formed for the sake of good men, in order to instruct them how they may live on friendly terms with one another, and partly for the sake of those who refuse to be instructed, whose spirit cannot be subdued, or softened, or hindered from plunging into evil.",
						"Laws, Morality" },
				{
						"Plato",
						"Ignorance, the root and the stem of every evil.",
						"Education, Good & Evil" },
				{
						"Plato",
						"If women are expected to do the same work as men, we must teach them the same things.",
						"Work" },
				{
						"Plato",
						"He who is of calm and happy nature will hardly feel the pressure of age, but to him who is of an opposite disposition youth and age are equally a burden.",
						"Age" },
				{ "Plato", "Friends have all things in common.", "Friendship" },
				{
						"Plato",
						"False words are not only evil in themselves, but they infect the soul with evil.",
						"Truth & Lies" },
				{ "Plato", "Death is not the worst than can happen to men.", "Life & Death" },
				{
						"Plato",
						"Bodily exercise, when compulsory, does no harm to the body; but knowledge which is acquired under compulsion obtains no hold on the mind.",
						"Knowledge" },
				{
						"Plato",
						"Be kind, for everyone you meet is fighting a hard battle.",
						"Miscellaneous" },
				{
						"Plato",
						"Astronomy compels the soul to look upwards and leads us from this world to another.",
						"Science" },
				{
						"Plato",
						"A tyrant is always stirring up some war or other, in order that the people may require a leader.",
						"War & Peace" },
				{
						"Blaise Pascal",
						"To ridicule philosophy is really to philosophize.",
						"Philosophy" },
				{
						"Blaise Pascal",
						"The last thing one discovers in composing a work is what to put first.",
						"Art" },
				{
						"Blaise Pascal",
						"The heart has its reasons, of which the mind knows nothing.",
						"Miscellaneous" },
				{
						"Blaise Pascal",
						"Men never do evil so completely and cheerfully as when they do it from religious conviction.",
						"Anti-Religion, Good & Evil" },
				{
						"Blaise Pascal",
						"Man's nature is not always to advance; it has its advances and retreats.",
						"Miscellaneous" },
				{
						"Blaise Pascal",
						"Man is equally incapable of seeing the nothingness from which he emerges and the infinity in which he is engulfed.",
						"Philosophy" },
				{
						"Blaise Pascal",
						"I maintain that, if everyone knew what others said about him, there would not be four friends in the world.",
						"Friendship" },
				{
						"Blaise Pascal",
						"I made this letter longer than usual because I lack the time to make it short.",
						"Writing" },
				{
						"Blaise Pascal",
						"Clarity of mind means clarity of passion, too; this is why a great and clear mind loves ardently and sees distinctly what it loves.",
						"Love" },
				{
						"Henry Mencken",
						"We must repsect the other fellow's religion, but only in the sense and to the extent that we respect his theory that his wife is beautiful and his children are smart.",
						"Anti-Religion" },
				{
						"Henry Mencken",
						"Wars are seldom caused by spontaneous hatreds between people, for peoples in general are too ignorant of one another to have grievances and too indifferent to what goes on beyond their borders to plan conquests. They must be urged to the slaughter by politicians who know how to alarm them.",
						"War & Peace" },
				{
						"Henry Mencken",
						"Under democracy one party always devotes its chief energies to trying to prove that the other party is unfit to rule - and both commonly succeed, and are right.",
						"Democracy" },
				{
						"Henry Mencken",
						"To wage a war for a purely moral reason is as absurd as to ravish a woman for a purely moral reason.",
						"War & Peace" },
				{
						"Henry Mencken",
						"The world always makes the assumption that the exposure of an error is identical with the discovery of truth--that the error and truth are simply opposite. They are nothing of the sort. What the world turns to, when it is cured on one error, is usually simply another error, and maybe one worse than the first one.",
						"Truth & Lies" },
				{
						"Henry Mencken",
						"The trouble with fighting for human freedom is that one spends most of one's time defending scoundrels. For it is against scoundrels that oppressive laws are first aimed, and oppression must be stopped at the beginning if it is to be stopped at all.",
						"Freedom, Laws" },
				{
						"Henry Mencken",
						"The one permanent emotion of the inferior man is fear - fear of the unknown, the complex, the inexplicable. What he wants above everything else is safety.",
						"Fear" },
				{
						"Henry Mencken",
						"The older I grow the more I distrust the familiar doctrine that age brings wisdom.",
						"Age, Wisdom" },
				{
						"Henry Mencken",
						"The government consists of a gang of men exactly like you and me. They have, taking one with another, no special talent for the business of government; they have only a talent for getting and holding office.",
						"Government" },
				{
						"Henry Mencken",
						"The essence of a self-reliant and autonomous culture is an unshakeable egoism.",
						"Pride" },
				{
						"Henry Mencken",
						"The difference between a moral man and a man of honor is that the latter regrets a discreditable act, even when it has worked and he has not been caught.",
						"Morality" },
				{
						"Henry Mencken",
						"The chief value of money lies in the fact that one lives in a world in which it is overestimated.",
						"Money" },
				{
						"Henry Mencken",
						"Say what you will about the Ten Commandments, you must always come back to the pleasant fact that there are only ten of them.",
						"Christianity" },
				{
						"Henry Mencken",
						"Religion is fundamentally opposed to everything I hold in veneration - courage, clear thinking, honesty, fairness, and, above all, love of the truth.",
						"Anti-Religion" },
				{
						"Henry Mencken",
						"Puritanism - the haunting fear that someone, somewhere, may be happy.",
						"Anti-Religion" },
				{
						"Henry Mencken",
						"Philosophy consists very largely of one philosopher arguing that all others are jackasses. He usually proves it, and I should add that he also usually proves that he is one himself.",
						"Philosophy" },
				{
						"Henry Mencken",
						"No one ever went broke underestimating the intelligence of the American public.",
						"America" },
				{
						"Henry Mencken",
						"Marriage is a wonderful institution, but who would want to live in an institution?",
						"Marriage" },
				{ "Henry Mencken", "Love is the triumph of imagination over intelligence.", "Love" },
				{
						"Henry Mencken",
						"Love is like war: easy to begin but very hard to stop.",
						"Love, War & Peace" },
				{
						"Henry Mencken",
						"It is the dull man who is always sure, and the sure man who is always dull.",
						"Miscellaneous" },
				{
						"Henry Mencken",
						"It is hard to believe that a man is telling the truth when you know that you would lie if you were in his place.",
						"Truth & Lies" },
				{
						"Henry Mencken",
						"In the United States, doing good has come to be, like patriotism, a favorite device of persons with something to sell.",
						"America" },
				{
						"Henry Mencken",
						"Imagine the Creator as a low comedian, and at once the world becomes explicable.",
						"Theology" },
				{
						"Henry Mencken",
						"I believe that all government is evil, and that trying to improve it is largely a waste of time.",
						"Government" },
				{
						"Henry Mencken",
						"Giving every man a vote has no more made men wise and free than Christianity has made them good.",
						"Voting" },
				{
						"Henry Mencken",
						"Freedom of the press is limited to those who own one.",
						"Freedom" },
				{
						"Henry Mencken",
						"For centuries, theologians have been explaining the unknowable in terms of the-not-worth-knowing.",
						"Theology" },
				{
						"Henry Mencken",
						"Faith may be defined briefly as an illogical belief in the occurrence of the improbable.",
						"Faith" },
				{ "Henry Mencken", "Every government is a scoundrel.", "Government" },
				{
						"Henry Mencken",
						"Every decent man is ashamed of the government he lives under.",
						"Government" },
				{
						"Henry Mencken",
						"Democracy is the theory that the common people know what they want and deserve to get it good and hard.",
						"Democracy" },
				{ "Henry Mencken", "Criticism is prejudice made plausible.", "Miscellaneous" },
				{
						"Henry Mencken",
						"Conscience is the inner voice that warns us somebody may be looking.",
						"Morality" },
				{
						"Henry Mencken",
						"Any man who afflicts the human race with ideas must be prepared to see them misunderstood.",
						"Ideas" },
				{
						"Henry Mencken",
						"All men are frauds. The only difference between them is that some admit it. I myself deny it.",
						"Miscellaneous" },
				{
						"Henry Mencken",
						"A poet more than thirty years old is simply an overgrown child.",
						"Poetry" },
				{
						"Henry Mencken",
						"A judge is a law student who marks his own examination papers.",
						"Laws" },
				{
						"Henry Mencken",
						"A Galileo could no more be elected president of the United States than he could be elected Pope of Rome. Both high posts are reserved for men favored by God with an extraordinary genius for swathing the bitter facts of life in bandages of self-illusion.",
						"America, Politics" },
				{
						"Henry Mencken",
						"A cynic is a man who, when he smells flowers, looks around for a coffin.",
						"Miscellaneous" },
				{
						"Henry Thoreau",
						"We must walk consciously only part way toward our goal, and then leap in the dark to our success.",
						"Motivation & Goals, Success" },
				{ "Henry Thoreau", "To be awake is to be alive.", "Sleep" },
				{
						"Henry Thoreau",
						"To be a philosopher is not merely to have subtle thoughts; but so to love wisdom as to live according to its dictates.",
						"Philosophy, Wisdom" },
				{
						"Henry Thoreau",
						"There are a thousand hacking at the branches of evil to one who is striking at the root.",
						"Good & Evil" },
				{
						"Henry Thoreau",
						"The price of anything is the amount of life you exchange for it.",
						"Life & Death, Money" },
				{
						"Henry Thoreau",
						"Success usually comes to those who are too busy to be looking for it.",
						"Success" },
				{
						"Henry Thoreau",
						"Nature, even when she is scant and thin outwardly, satisfies us still by the assurance of a certain generosity at the roots.",
						"Nature" },
				{ "Henry Thoreau", "Men are born to succeed, not fail.", "Failure, Success" },
				{ "Henry Thoreau", "It is not what you look at, but what you see.", "Miscellaneous" },
				{ "Henry Thoreau", "In the long run, you only hit what you aim at.", "Risk" },
				{
						"Henry Thoreau",
						"If we will be quiet and ready enough, we shall find compensation in every disappointment.",
						"Failure" },
				{
						"Henry Thoreau",
						"I went out to the country so i could examine the simple things in life.",
						"Miscellaneous" },
				{
						"Henry Thoreau",
						"I have learned, that if one advances confidently in the direction of his dreams, and endeavors to live the life he has imagined, he will meet with a success unexpected in common hours.",
						"Motivation & Goals" },
				{
						"Henry Thoreau",
						"Every generation laughs at the old fashions, but follows religiously the new.",
						"Youth" },
				{
						"Henry Thoreau",
						"Beware of all enterprises that require new clothes.",
						"Miscellaneous" },
				{ "Henry Thoreau", "As if you could kill time without injuring eternity.", "Time" },
				{ "Henry Thoreau", "Any fool can make a rule.", "Miscellaneous" },
				{
						"Saint Augustine",
						"What, then, is time? If no one asks me, I know what it is. If I wish to explain it to him who asks, I do not know.",
						"Time" },
				{ "Saint Augustine", "Unless you believe, you will not understand.", "Faith" },
				{
						"Saint Augustine",
						"This is the very perfection of a man, to find out his own imperfections.",
						"Virtue" },
				{
						"Saint Augustine",
						"The World is a book, and those who do not travel read only a page.",
						"Miscellaneous" },
				{
						"Saint Augustine",
						"The words printed here are concepts. You must go through the experiences.",
						"Miscellaneous" },
				{ "Saint Augustine", "The purpose of all wars, is peace.", "War & Peace" },
				{
						"Saint Augustine",
						"The higher your structure is to be, the deeper must be its foundation.",
						"Success" },
				{ "Saint Augustine", "The argument is at an end.", "Debate" },
				{
						"Saint Augustine",
						"Since love grows within you, so beauty grows. For love is the beauty of the soul.",
						"Love" },
				{ "Saint Augustine", "Sin is its own punishment.", "Morality" },
				{
						"Saint Augustine",
						"Pray as though everything depended on God. Work as though everything depended on you.",
						"God, Prayer" },
				{ "Saint Augustine", "Patience is the companion of wisdom.", "Wisdom" },
				{ "Saint Augustine", "Never judge a philosophy by its abuse.", "Philosophy" },
				{
						"Saint Augustine",
						"Men go abroad to wonder at the heights of mountains, at the huge waves of the sea, at the long courses of the rivers, at the vast compass of the ocean, at the circular motions of the stars, and they pass by themselves without wondering.",
						"Miscellaneous" },
				{
						"Saint Augustine",
						"Indeed, man wishes to be happy even when he so lives as to make happiness impossible.",
						"Morality" },
				{ "Saint Augustine", "I was in love with loving.", "Love" },
				{
						"Saint Augustine",
						"Humility is the foundation of all the other virtues hence, in the soul in which this virtue does not exist there cannot be any other virtue except in mere appearance.",
						"Virtue" },
				{
						"Saint Augustine",
						"He that is kind is free, though he is a slave; he that is evil is a slave, though he be a king.",
						"Freedom, Virtue" },
				{ "Saint Augustine", "God loves each of us as if there were only one of us.", "God" },
				{
						"Saint Augustine",
						"God is more truly imagined than expressed, and He exists more truly than He is imagined.",
						"God" },
				{
						"Saint Augustine",
						"God does not give heed to the ambitiousness of our prayers, because he is always ready to give to us his light, not a visible light but an intellectual and spiritual one; but we are not always ready to receive it when we turn aside and down to other things out of a desire for temporal things.",
						"Prayer" },
				{ "Saint Augustine", "Give me chastity and continence, but not yet.", "Morality" },
				{
						"Saint Augustine",
						"Find out how much God has given you and from it take what you need; the remainder is needed by others.",
						"Charity" },
				{
						"Saint Augustine",
						"Faith is to believe what you do not see; the reward of this faith is to see what you believe.",
						"Faith" },
				{
						"Saint Augustine",
						"Better to have loved and lost, than to have never loved at all.",
						"Love" },
				{
						"Saint Augustine",
						"Beauty is indeed a good gift of God; but that the good may not think it a great good, God dispenses it even to the wicked.",
						"Beauty" },
				{
						"Ambrose Bierce",
						"You are not permitted to kill a woman who has wronged you, but nothing forbids you to reflect that she is growing older every minute. You are avenged 1440 times a day.",
						"Age" },
				{
						"Ambrose Bierce",
						"Wedding: a ceremony at which two persons undertake to become one, one undertakes to become nothing, and nothing undertakes to become supportable.",
						"Marriage" },
				{
						"Ambrose Bierce",
						"We know what happens to people who stay in the middle of the road. They get run over.",
						"Politics" },
				{
						"Ambrose Bierce",
						"The world has suffered more from the ravages of ill-advised marriages than from virginity.",
						"Marriage" },
				{
						"Ambrose Bierce",
						"The small part of ignorance that we arrange and classify we give the name of knowledge.",
						"Education" },
				{
						"Ambrose Bierce",
						"The gambling known as business looks with austere disfavor upon the business known as gambling.",
						"Money" },
				{ "Ambrose Bierce", "The covers of this book are too far apart.", "Reading" },
				{
						"Ambrose Bierce",
						"Speak when you are angry and you will make the best speech you will ever regret.",
						"Anger" },
				{
						"Ambrose Bierce",
						"PRESIDENT, n. The leading figure in a small group of men of whom — and of whom only — it is positively known that immense numbers of their countrymen did not want any of them for President.",
						"Presidency" },
				{
						"Ambrose Bierce",
						"PRESIDENCY, n. The greased pig in the field game of American politics.",
						"Presidency" },
				{
						"Ambrose Bierce",
						"Philosophy: A route of many roads leading from nowhere to nothing.",
						"Philosophy" },
				{
						"Ambrose Bierce",
						"Ocean: A body of water occupying 2/3 of a world made for man...who has no gills.",
						"Anti-Religion" },
				{
						"Ambrose Bierce",
						"In our civilization, and under our republican form of government, intelligence is so highly honored that it is rewarded by exemption from the cares of office.",
						"Intelligence, Politics" },
				{
						"Ambrose Bierce",
						"I think that a young state, like a young virgin, should modestly stay at home, and wait the application of suitors for an alliance with her; and not run about offering her amity to all the world; and hazarding their refusal. Our virgin is a jolly one; and tho at present not very rich, will in time be a great fortune, and where she has a favorable predisposition, it seems to me well worth cultivating.",
						"Politics" },
				{
						"Ambrose Bierce",
						"Death is not the end. There remains the litigation over the estate.",
						"Life & Death" },
				{
						"Ambrose Bierce",
						"Being is desirable because it is identical with Beauty, and Beauty is loved because it is Being. We ourselves possess Beauty when we are true to our own being; ugliness is in going over to another order; knowing ourselves, we are beautiful; in self-ignorance, we are ugly.",
						"Beauty" },
				{
						"Ambrose Bierce",
						"All are lunatics, but he who can analyze his delusions is called a philosopher.",
						"Philosophy" },
				{
						"Ambrose Bierce",
						"Admiration. Our polite recognition of another's resemblance to ourselves.",
						"Miscellaneous" },
				{
						"Ambrose Bierce",
						"A grave is a place where the dead are laid to await the coming of the medical student.",
						"Life & Death" },
				{
						"Henry Ward Beecher",
						"Young love is a flame; very pretty, often very hot and fierce, but still only light and flickering. The love of the older and disciplined heart is as coals, deep burning, unquenchable.",
						"Love" },
				{
						"Henry Ward Beecher",
						"The unthankful heart... discovers no mercies; but let the thankful heart sweep through the day and, as the magnet finds the iron, so it will find, in every hour, some heavenly blessings!",
						"Thanksgiving" },
				{
						"Henry Ward Beecher",
						"The philosophy of one century is the common sense of the next.",
						"Philosophy" },
				{
						"Henry Ward Beecher",
						"The art of being happy lies in the power of extracting happiness from common things.",
						"Happiness" },
				{
						"Henry Ward Beecher",
						"It is not the going out of port, but the coming in, that determines the success of a voyage.",
						"Success" },
				{
						"Henry Ward Beecher",
						"I never knew how to worship until I knew how to love.",
						"Love, Religion" },
				{
						"Marcel Proust",
						"What a profound significance small things assume when the woman we love conceals them from us.",
						"Love" },
				{
						"Marcel Proust",
						"We must never be afraid to go too far, for success lies just beyond.",
						"Success" },
				{
						"Marcel Proust",
						"We don't receive wisdom; we must discover it for ourselves after a journey that no one can take for us or spare us.",
						"Wisdom" },
				{
						"Marcel Proust",
						"We are healed from suffering only by experiencing it to the full.",
						"Miscellaneous" },
				{
						"Marcel Proust",
						"Time, which changes people, does not alter the image we have retained of them.",
						"Time" },
				{
						"Marcel Proust",
						"Time passes, and little by little everything that we have spoken in falsehood becomes true.",
						"Time, Truth & Lies" },
				{
						"Marcel Proust",
						"There is no man, however wise, who has not at some period of his youth said things, or lived in a way the consciousness of which is so unpleasant to him in later life that he would gladly, if he could, expunge it from his memory.",
						"Miscellaneous" },
				{
						"Marcel Proust",
						"There are perhaps no days of our childhood we lived so fully as those we spent with a favorite book.",
						"Children, Reading" },
				{
						"Marcel Proust",
						"The time which we have at our disposal every day is elastic; the passions we feel expand it, those that we inspire contract it, and habit fills up what remains.",
						"Time" },
				{
						"Marcel Proust",
						"The real voyage of discovery consists not in seeking new landscapes but in having new eyes.",
						"Miscellaneous" },
				{
						"Marcel Proust",
						"The paradoxes of today are the prejudices of tomorrow, since the most benighted and the most deplorable prejudices have had their moment of novelty when fashion lent them its fragile grace.",
						"Miscellaneous" },
				{
						"Marcel Proust",
						"The only thing that does not change is that at any and every time it appears that there have been 'great changes.'",
						"Change" },
				{ "Marcel Proust", "The only paradise is paradise lost.", "Miscellaneous" },
				{
						"Marcel Proust",
						"The moments of the past do not remain still; they retain in our memory the motion which drew them towards the future, towards a future which has itself become the past, and draw us on in their train.",
						"Time" },
				{
						"Marcel Proust",
						"The mistakes made by doctors are innumerable. They err habitually on the side of optimism as to treatment, of pessimism as to the outcome.",
						"Health" },
				{
						"Marcel Proust",
						"The charms of the passing woman are generally in direct proportion to the swiftness of her passing.",
						"" },
				{
						"Marcel Proust",
						"The bonds that unite another person to our self exist only in our mind.",
						"Miscellaneous" },
				{
						"Marcel Proust",
						"People wish to learn to swim and at the same time to keep one foot on the ground.",
						"Risk" },
				{
						"Marcel Proust",
						"People who are not in love fail to understand how an intelligent man can suffer because of a very ordinary woman. This is like being surprised that anyone should be stricken with cholera because of a creature so insignificant as the comma bacillus.",
						"Love" },
				{
						"Marcel Proust",
						"Our memory is like a shop in the window of which is exposed now one, now another photograph of the same person. And as a rule the most recent exhibit remains for some time the only one to be seen.",
						"Miscellaneous" },
				{
						"Marcel Proust",
						"Like many intellectuals, he was incapable of saying a simple thing in a simple way.",
						"Clever Jabs, Talking" },
				{
						"Marcel Proust",
						"Lies are essential to humanity. They are perhaps as important as the pursuit of pleasure and moreover are dictated by that pursuit.",
						"Truth & Lies" },
				{ "Marcel Proust", "Let us leave pretty women to men devoid of imagination.", "" },
				{
						"Marcel Proust",
						"Let us be grateful to people who make us happy; they are the charming gardeners who make our souls blossom.",
						"Happiness" },
				{
						"Marcel Proust",
						"Less disappointing than life, great works of art do not begin by giving us all their best.",
						"Art" },
				{
						"Marcel Proust",
						"It is seldom indeed that one parts on good terms, because if one were on good terms, one would not part.",
						"Miscellaneous" },
				{
						"Marcel Proust",
						"It is often hard to bear the tears that we ourselves have caused.",
						"Miscellaneous" },
				{
						"Marcel Proust",
						"It is always thus, impelled by a state of mind which is destined not to last, that we make our irrevocable decisions.",
						"Decisions" },
				{
						"Marcel Proust",
						"In his younger days a man dreams of possessing the heart of the woman whom he loves; later, the feeling that he possesses the heart of a woman may be enough to make him fall in love with her.",
						"Love" },
				{
						"Marcel Proust",
						"In a separation it is the one who is not really in love who says the more tender things.",
						"Love" },
				{
						"Marcel Proust",
						"Illness is the doctor to whom we pay most heed; to kindness, to knowledge, we make promise only; pain we obey.",
						"Miscellaneous" },
				{
						"Marcel Proust",
						"If only for the sake of elegance, I try to remain morally pure.",
						"Morality" },
				{
						"Marcel Proust",
						"If a little dreaming is dangerous, the cure for it is not to dream less but to dream more, to dream all the time.",
						"Miscellaneous" },
				{
						"Marcel Proust",
						"I perceived that to express those impressions, to write that essential book, which is the only true one, a great writer does not, in the current meaning of the word, invent it, but, since it exists already in each one of us, interprets it. The duty and the task of a writer are those of an interpreter.",
						"Writing" },
				{
						"Marcel Proust",
						"Happiness serves hardly any other purpose than to make unhappiness possible.",
						"Happiness" },
				{
						"Marcel Proust",
						"Happiness is beneficial for the body, but it is grief that develops the powers of the mind.",
						"Happiness, Thinking" },
				{
						"Marcel Proust",
						"For each illness that doctors cure with medicine, they provoke ten in healthy people by inoculating them with the virus that is a thousand times more powerful than any microbe: the idea that one is ill.",
						"Health" },
				{
						"Marcel Proust",
						"Every reader finds himself. The writer's work is merely a kind of optical instrument that makes it possible for the reader to discern what, without this book, he would perhaps never have seen in himself.",
						"Reading, Writing" },
				{
						"Marcel Proust",
						"As soon as one is unhappy one becomes moral.",
						"Happiness, Morality" },
				{
						"Marcel Proust",
						"Any mental activity is easy if it need not take reality into account.",
						"Thinking" },
				{
						"Marcel Proust",
						"A powerful idea communicates some of its strength to him who challenges it.",
						"Ideas" },
				{ "Baltasar Gracian", "Without courage, wisdom bears no fruit.", "Courage, Wisdom" },
				{
						"Baltasar Gracian",
						"True friendship multiplies the good in life and divides its evils. Strive to have friends, for life without friends is like life on a desert island….to find one real friend in a lifetime is good fortune; to keep him is a blessing.",
						"Friendship" },
				{
						"Baltasar Gracian",
						"The wise man does at once what the fool does finally.",
						"Wisdom, Work" },
				{ "Baltasar Gracian", "The path to greatness is along with others.", "Success" },
				{
						"Baltasar Gracian",
						"One deceit needs many others, and so the whole house is built in the air and must soon come to the ground.",
						"Truth & Lies" },
				{
						"Baltasar Gracian",
						"Never open the door to a lesser evil, for other and greater ones invariably slink in after it.",
						"Morality" },
				{
						"Baltasar Gracian",
						"Nature scarcely ever gives us the very best; for that we must have recourse to art.",
						"Art, Nature" },
				{
						"Baltasar Gracian",
						"It is better to sleep on things beforehand than lie awake about them afterward.",
						"Sleep" },
				{
						"Baltasar Gracian",
						"Great ability develops and reveals itself increasingly with every new assignment.",
						"Experience" },
				{
						"Baltasar Gracian",
						"Don't take the wrong side of an argument just because your opponent has taken the right side.",
						"Debate" },
				{
						"Baltasar Gracian",
						"Always leave something to wish for; otherwise you will be miserable from your very happiness.",
						"Happiness" },
				{
						"Baltasar Gracian",
						"A wise man gets more use from his enemies than a fool from his friends.",
						"Wisdom" },
				{
						"Baltasar Gracian",
						"A single lie destroys a whole reputation of integrity.",
						"Truth & Lies" },
				{ "Socrates", "Wisdom begins in wonder.", "Wisdom" },
				{ "Socrates", "What you cannot enforce, do not command.", "Miscellaneous" },
				{
						"Socrates",
						"True wisdom comes to each of us when we realize how little we understand about life, ourselves, and the world around us.",
						"Wisdom" },
				{ "Socrates", "To find yourself, think for yourself.", "Thinking" },
				{
						"Socrates",
						"There is only one good, knowledge, and one evil, ignorance.",
						"Good & Evil, Knowledge" },
				{ "Socrates", "The only true wisdom is in knowing you know nothing.", "Wisdom" },
				{
						"Socrates",
						"The nearest way to glory is to strive to be what you wish to be thought to be.",
						"Virtue" },
				{
						"Socrates",
						"Remember that there is nothing stable in human affairs; therefore avoid undue elation in prosperity, or undue depression in adversity.",
						"Miscellaneous" },
				{
						"Socrates",
						"Only the extremely ignorant or the extremely intelligent can resist change.",
						"Miscellaneous" },
				{
						"Socrates",
						"One thing only I know, and that is that I know nothing.",
						"Knowledge" },
				{
						"Socrates",
						"My advice to you is to get married. If you find a good wife you'll be happy; if not you'll become a philosopher.",
						"Marriage, Philosophy" },
				{
						"Socrates",
						"Let him that would move the world first move himself.",
						"Motivation & Goals" },
				{
						"Socrates",
						"If all our misfortunes were laid in one common heap, whence everyone must take an equal portion, most people would be content to take their own and depart.",
						"Miscellaneous" },
				{
						"Socrates",
						"If a man is proud of his wealth, he should not be praised until it is known how he employs it.",
						"Money" },
				{
						"Socrates",
						"I don't know why I did it, I don't know why I enjoyed it, and I don't know why I'll do it again.",
						"Miscellaneous" },
				{
						"Socrates",
						"He who is not contented with what he has, would not be contented with what he would like to have.",
						"Money" },
				{ "Socrates", "He is richest who is content with the least.", "Money" },
				{
						"Socrates",
						"False words are not only evil in themselves, but they infect the soul with evil.",
						"Truth & Lies" },
				{
						"Socrates",
						"Contentment is natural wealth, luxury is artificial poverty.",
						"Money" },
				{
						"Socrates",
						"Children today are tyrants. They contradict their parents, gobble their food, and tyrannize their teachers.",
						"Children" },
				{ "Socrates", "Beauty is a short-lived tyranny.", "Beauty" },
				{
						"Socrates",
						"Bad men live that they may eat and drink, whereas good men eat and drink that they may live.",
						"Food" },
				{
						"Socrates",
						"As to marriage or celibacy, let a man take which course he will; he will be sure to repent it.",
						"Marriage" },
				{ "Socrates", "An unexamined life is not worth living.", "Philosophy" },
				{
						"Socrates",
						"A husband is what's left of the lover after the nerve has been extracted.",
						"Marriage" },
				{ "Aristotle", "Youth is easily deceived because it is quick to hope.", "Youth" },
				{ "Aristotle", "Wit is educated insolence.", "Education" },
				{
						"Aristotle",
						"What it lies in our power to do, it lies in our power not to do.",
						"Miscellaneous" },
				{ "Aristotle", "We make war that we may live in peace.", "War & Peace" },
				{
						"Aristotle",
						"We live in deeds, not years: In thoughts not breaths; In feelings, not in figures on a dial. We should count time by heart throbs. He most lives Who thinks most, feels the noblest, acts the best.",
						"Miscellaneous" },
				{
						"Aristotle",
						"We are what we repeatedly do. Excellence, therefore, is not an act, but a habit.",
						"Success" },
				{
						"Aristotle",
						"Those who excel in virtue have the best right of all to rebel, but then they are of all men the least inclined to do so.",
						"Virtue" },
				{ "Aristotle", "The secret to humor is surprise.", "Humor" },
				{
						"Aristotle",
						"The roots of education are bitter, but the fruit is sweet.",
						"Education" },
				{
						"Aristotle",
						"The least initial deviation from the truth is multiplied later a thousandfold.",
						"Truth & Lies" },
				{ "Aristotle", "The gods too are fond of a joke.", "God" },
				{
						"Aristotle",
						"The educated differ from the uneducated as much as the living from the dead.",
						"Education" },
				{
						"Aristotle",
						"The appropriate age for marriage is around eighteen for girls and thirty-seven for men.",
						"Marriage" },
				{
						"Aristotle",
						"The aim of art is to represent not the outward appearance of things, but their inward significance.",
						"Art" },
				{
						"Aristotle",
						"Strange that the vanity which accompanies beauty -- excusable, perhaps, when there is such great beauty, or at any rate understandable -- should persist after the beauty was gone.",
						"Beauty" },
				{
						"Aristotle",
						"Probable impossibilities are to be preferred to improbable possibilities.",
						"Philosophy" },
				{
						"Aristotle",
						"Personal beauty is a greater recommendation than any letter of reference.",
						"Beauty" },
				{ "Aristotle", "One swallow does not make a summer.", "Miscellaneous" },
				{
						"Aristotle",
						"Of all the varieties of virtues, liberalism is the most beloved.",
						"Virtue" },
				{
						"Aristotle",
						"No one would choose a friendless existence on condition of having all the other things in the world.",
						"Friendship" },
				{
						"Aristotle",
						"No great genius has ever existed without some touch of madness.",
						"Genius" },
				{ "Aristotle", "Nature does nothing uselessly.", "Nature" },
				{
						"Aristotle",
						"My best friend is the man who in wishing me well wishes it for my sake.",
						"Friendship" },
				{
						"Aristotle",
						"Mothers are fonder than fathers of their children because they are more certain they are their own.",
						"Children" },
				{
						"Aristotle",
						"Moral excellence comes about as a result of habit. We become just by doing just acts, temperate by doing temperate acts, brave by doing brave acts.",
						"Morality" },
				{
						"Aristotle",
						"Men create gods after their own image, not only with regard to their form but with regard to their mode of life.",
						"God" },
				{
						"Aristotle",
						"Men acquire a particular quality by constantly acting in a particular way.",
						"Miscellaneous" },
				{ "Aristotle", "Man is by nature a political animal.", "Politics" },
				{ "Aristotle", "Love is composed of a single soul inhabiting two bodies.", "Love" },
				{ "Aristotle", "Liars when they speak the truth are not believed.", "Truth & Lies" },
				{ "Aristotle", "It is unbecoming for young men to utter maxims.", "Quotations" },
				{
						"Aristotle",
						"It is the mark of an educated mind to be able to entertain a thought without accepting it.",
						"Education, Ideas" },
				{
						"Aristotle",
						"It is not once nor twice but times without number that the same ideas make their appearance in the world.",
						"Ideas" },
				{
						"Aristotle",
						"It is best to rise from life as from a banquet, neither thirsty nor drunken.",
						"Morality" },
				{
						"Aristotle",
						"In nine cases out of ten, a woman had better show more affection than she feels.",
						"" },
				{
						"Aristotle",
						"If liberty and equality, as is thought by some, are chiefly to be found in democracy, they will be best attained when all persons alike share in government to the utmost.",
						"Democracy, Government, Liberty" },
				{
						"Aristotle",
						"I have gained this from philosophy: that I do without being commanded what others do only from fear of the law.",
						"Philosophy" },
				{
						"Aristotle",
						"Humor is the only test of gravity, and gravity of humor; for a subject which will not bear raillery is suspicious, and a jest which will not bear serious examination is false wit.",
						"Humor" },
				{ "Aristotle", "Hope is a waking dream.", "Hope" },
				{ "Aristotle", "Happiness depends upon ourselves.", "Happiness" },
				{ "Aristotle", "Friendship is a single soul dwelling in two bodies.", "Friendship" },
				{
						"Aristotle",
						"Fear is pain arising from the anticipation of evil.",
						"Fear, Good & Evil" },
				{
						"Aristotle",
						"Dignity does not consist in possessing honors, but in deserving them.",
						"Virtue" },
				{
						"Aristotle",
						"Democracy is when the indigent, and not the men of property, are the rulers.",
						"Democracy" },
				{
						"Aristotle",
						"Courage is the first of human qualities because it is the quality which guarantees the others.",
						"Courage" },
				{
						"Aristotle",
						"Character is that which reveals moral purpose, exposing the class of things a man chooses and avoids.",
						"Morality" },
				{
						"Aristotle",
						"Bring your desires down to your present means. Increase them only when your increased means permit.",
						"Success" },
				{
						"Aristotle",
						"Both oligarch and tyrant mistrust the people, and therefore deprive them of their arms.",
						"Government" },
				{
						"Aristotle",
						"Bashfulness is an ornament to youth, but a reproach to old age.",
						"Age" },
				{ "Aristotle", "Bad men are full of repentance.", "Morality" },
				{
						"Aristotle",
						"Anybody can become angry - that is easy, but to be angry with the right person and to the right degree and at the right time and for the right purpose, and in the right way - that is not within everybody's power and is not easy.",
						"Anger" },
				{ "Aristotle", "All virtue is summed up in dealing justly.", "Virtue" },
				{ "Aristotle", "All men desire to know.", "Philosophy" },
				{
						"Aristotle",
						"All human actions have one or more of these seven causes: chance, nature, compulsions, habit, reason, passion, desire.",
						"Miscellaneous" },
				{
						"Aristotle",
						"A tyrant must put on the appearance of uncommon devotion to religion. Subjects are less apprehensive of illegal treatment from a ruler whom they consider god-fearing and pious. On the other hand, they do less easily move against him, believing that he has the gods on his side.",
						"Government" },
				{
						"Isaac Newton",
						"We build too many walls and not enough bridges.",
						"Miscellaneous" },
				{
						"Isaac Newton",
						"To me there has never been a higher source of earthly honor or distinction than that connected with advances in science.",
						"Science" },
				{
						"Isaac Newton",
						"This most beautiful system [The Universe] could only proceed from the dominion of an intelligent and powerful Being.",
						"God" },
				{
						"Isaac Newton",
						"Tact is the knack of making a point without making an enemy.",
						"Enemies" },
				{ "Isaac Newton", "No great discovery was ever made without a bold guess.", "Risk" },
				{
						"Isaac Newton",
						"If I have seen further, it is by standing on the shoulders of giants.",
						"Knowledge" },
				{
						"Isaac Newton",
						"If I have ever made any valuable discoveries, it has been owing more to patient attention, than to any other talent.",
						"Miscellaneous" },
				{
						"Isaac Newton",
						"If I am anything, which I highly doubt, I have made myself so by hard work.",
						"Work" },
				{
						"Isaac Newton",
						"I keep the subject of my inquiry constantly before me, and wait till the first dawning opens gradually, by little and little, into a full and clear light.",
						"Science" },
				{
						"Isaac Newton",
						"I do not know what I may appear to the world; but to myself I seem to have been only like a boy playing on the seashore, and diverting myself in now and then finding a smoother pebble or a prettier shell than ordinary, whilst the great ocean of truth lay all undiscovered before me.",
						"Knowledge" },
				{
						"Isaac Newton",
						"A man may imagine things that are false, but he can only understand things that are true, for if the things be false, the apprehension of them is not understanding.",
						"Truth & Lies" },
				{
						"Charles Darwin",
						"We can allow satellites, planets, suns, universe, nay whole systems of universe[s], to be governed by laws, but the smallest insect, we wish to be created at once by special act.",
						"Anti-God" },
				{
						"Charles Darwin",
						"The fact of evolution is the backbone of biology, and biology is thus in the peculiar position of being a science founded on an improved theory, is it then a science or faith?",
						"Science" },
				{
						"Charles Darwin",
						"Nothing before had ever made me thoroughly realise, though I had read various scientific books, that science consists in grouping facts so that general laws or conclusions may be drawn from them.",
						"Science" },
				{
						"Charles Darwin",
						"Man still bears in his bodily frame the indelible stamp of his lowly origin.",
						"Miscellaneous" },
				{
						"Charles Darwin",
						"It is those who know little, and not those who know much, who so positively assert that this or that problem will never be solved by science.",
						"Science" },
				{
						"Charles Darwin",
						"In the struggle for survival, the fittest win out at the expense of their rivals because they succeed in adapting themselves best to their environment.",
						"Miscellaneous" },
				{
						"Charles Darwin",
						"Ignorance more frequently begets confidence than does knowledge.",
						"Knowledge" },
				{
						"Charles Darwin",
						"I love fools’ experiments. I am always making them.",
						"Science" },
				{
						"Charles Darwin",
						"Doing what little one can to increase the general stock of knowledge is as respectable an object of life, as one can in any likelihood pursue.",
						"Knowledge" },
				{
						"Charles Darwin",
						"As for a future life, every man must judge for himself between conflicting vague probabilities.",
						"Miscellaneous" },
				{
						"Charles Darwin",
						"A scientific man ought to have no wishes, no affections, -- a mere heart of stone.",
						"Science" },
				{
						"Leonardo da Vinci",
						"You do ill if you praise, but worse if you censure, what you do not understand.",
						"Miscellaneous" },
				{
						"Leonardo da Vinci",
						"Where the spirit does not work with the hand, there is no art.",
						"Art" },
				{
						"Leonardo da Vinci",
						"The faculty of imagination is both the rudder and the bridle of the senses.",
						"Miscellaneous" },
				{ "Leonardo da Vinci", "Simplicity is the ultimate sophistication.", "Intelligence" },
				{
						"Leonardo da Vinci",
						"Once you have flown, you will walk the earth with your eyes turned skywards, for there you have been, and there you long to return.",
						"Miscellaneous" },
				{
						"Leonardo da Vinci",
						"Iron rusts from disuse; stagnant water loses its purity and in cold weather becomes frozen; even so does inaction sap the vigor of the mind.",
						"Thinking" },
				{
						"Leonardo da Vinci",
						"I have from an early age abjured the use of meat, and the time will come when men will look upon the murder of animals as they now look upon the murder of men.",
						"Animals" },
				{
						"Leonardo da Vinci",
						"As every divided kingdom falls, so every mind divided between many studies confounds and saps itself.",
						"Education" },
				{
						"Leonardo da Vinci",
						"As a well-spent day brings happy sleep, so life well used brings happy death.",
						"Life & Death" },
				{
						"Leonardo da Vinci",
						"Anyone who invokes authors in discussion is not using his intelligence but his memory.",
						"Talking" },
				{
						"Leonardo da Vinci",
						"All our knowledge has its origin in our perceptions.",
						"Knowledge" },
				{ "Leonardo da Vinci", "A well-spent day brings happy sleep.", "Sleep" },
				{
						"Siddhartha Buddha",
						"What we are today comes from our thoughts of yesterday and our present thoughts build our life tomorrow. Our life is the creation of our mind.",
						"Thinking" },
				{
						"Siddhartha Buddha",
						"To be idle is a short road to death and to be diligent is a way of life; foolish people are idle, wise people are diligent.",
						"Work" },
				{
						"Siddhartha Buddha",
						"On life's journey faith is nourishment, virtuous deeds are a shelter, wisdom is the light by day and right mindfulness is the protection by night. If a man lives a pure life, nothing can destroy him.",
						"Morality" },
				{
						"Siddhartha Buddha",
						"Neither fire nor wind, birth nor death can erase our good deeds.",
						"Miscellaneous" },
				{
						"Siddhartha Buddha",
						"Just as a candle cannot burn without fire, men cannot live without a spiritual life.",
						"God" },
				{ "Siddhartha Buddha", "It is your mind that creates this world.", "Thinking" },
				{
						"Siddhartha Buddha",
						"It is better to conquer yourself than to win a thousand battles. Then the victory is yours. It cannot be taken from you, not by angels or by demons, heaven or hell.",
						"Miscellaneous" },
				{
						"Siddhartha Buddha",
						"It is a man's own mind, not his enemy or foe, that lures him to evil ways.",
						"Good & Evil" },
				{
						"Siddhartha Buddha",
						"In a controversy the instant we feel anger we have already ceased striving for the truth, and have begun striving for ourselves.",
						"Anger" },
				{
						"Siddhartha Buddha",
						"If we could see the miracle of a single flower clearly, our whole life would change.",
						"Nature" },
				{
						"Siddhartha Buddha",
						"I never see what has been done; I only see what remains to be done.",
						"Work" },
				{
						"Siddhartha Buddha",
						"I do not believe in a fate that falls on men however they act; but I do believe in a fate that falls on them unless they act.",
						"Motivation & Goals" },
				{
						"Siddhartha Buddha",
						"However many holy words you read, however many you speak, what good will they do you if you do not act on upon them?",
						"Miscellaneous" },
				{
						"Siddhartha Buddha",
						"Holding on to anger is like grasping a hot coal with the intent of throwing it at someone else; you are the one who gets burned.",
						"Anger" },
				{
						"Siddhartha Buddha",
						"He who loves 50 people has 50 woes; he who loves no one has no woes.",
						"Love" },
				{ "Siddhartha Buddha", "He is able who thinks he is able.", "Motivation & Goals" },
				{
						"Siddhartha Buddha",
						"Do not dwell in the past, do not dream of the future, concentrate the mind on the present moment.",
						"Time" },
				{
						"Siddhartha Buddha",
						"Better than a thousand hollow words, is one word that brings peace.",
						"War & Peace" },
				{
						"Siddhartha Buddha",
						"Anger will never disappear so long as thoughts of resentment are cherished in the mind. Anger will disappear just as soon as thoughts of resentment are forgotten.",
						"Anger" },
				{
						"Siddhartha Buddha",
						"An idea that is developed and put into action is more important than an idea that exists only as an idea.",
						"Ideas" },
				{
						"Thomas Edison",
						"What a man's mind can create, man's character can control.",
						"Virtue" },
				{
						"Thomas Edison",
						"What a man's mind can create, man's character can control.",
						"Miscellaneous" },
				{
						"Thomas Edison",
						"We don't know a millionth of one percent about anything.",
						"Education" },
				{
						"Thomas Edison",
						"Until man duplicates a blade of grass, nature can laugh at his so-called scientific knowledge.",
						"Science" },
				{
						"Thomas Edison",
						"To invent, you need a good imagination and a pile of junk.",
						"Miscellaneous" },
				{ "Thomas Edison", "There's a way to do it better - find it.", "Motivation & Goals" },
				{
						"Thomas Edison",
						"There will one day spring from the brain of science a machine or force so fearful in its potentialities, so absolutely terrifying, that even man, the fighter, who will dare torture and death in order to inflict torture and death, will be appalled, and so abandon war forever.",
						"War & Peace" },
				{ "Thomas Edison", "There is time for everything.", "Time" },
				{ "Thomas Edison", "There is no substitute for hard work.", "Work" },
				{
						"Thomas Edison",
						"There is no expedient to which a man will not go to avoid the labor of thinking.",
						"Thinking" },
				{ "Thomas Edison", "The value of an idea lies in the using of it.", "Ideas" },
				{
						"Thomas Edison",
						"The best thinking has been done in solitude. The worst has been done in turmoil.",
						"Thinking" },
				{
						"Thomas Edison",
						"Show me a thoroughly satisfied man and I will show you a failure.",
						"Success" },
				{ "Thomas Edison", "Religion is all bunk.", "Religion" },
				{
						"Thomas Edison",
						"Opportunity is missed by most people because it comes dressed in overalls and looks like work.",
						"Success" },
				{
						"Thomas Edison",
						"Non-violence leads to the highest ethics, which is the goal of all evolution. Until we stop harming all other living beings, we are still savages.",
						"War & Peace" },
				{
						"Thomas Edison",
						"Nearly every man who develops an idea works it up to the point where it looks impossible, and then he gets discouraged. That's not the place to become discouraged.",
						"Ideas" },
				{
						"Thomas Edison",
						"Many of life's failures are people who did not realize how close they were to success when they gave up.",
						"Success" },
				{
						"Thomas Edison",
						"Just because something doesn't do what you planned it to do doesn't mean it's useless.",
						"Miscellaneous" },
				{
						"Thomas Edison",
						"It is astonishing what an effort it seems to be for many people to put their brains definitely and systematically to work.",
						"Work" },
				{
						"Thomas Edison",
						"If we all did the things we are capable of doing, we would literally astound ourselves.",
						"Miscellaneous" },
				{
						"Thomas Edison",
						"I have not failed. I've just found 10,000 ways that won't work.",
						"Success" },
				{
						"Thomas Edison",
						"I have friends in overalls whose friendship I would not swap for the favor of the kings of the world.",
						"Friendship" },
				{
						"Thomas Edison",
						"I continue to find my greatest pleasure, and so my reward, in the work that precedes what the world calls success.",
						"Success, Work" },
				{
						"Thomas Edison",
						"I am not discouraged, because every wrong attempt discarded is another step forward.",
						"Motivation & Goals" },
				{
						"Thomas Edison",
						"Hell, there are no rules here - we're trying to accomplish something.",
						"Motivation & Goals" },
				{ "Thomas Edison", "Great ideas originate in the muscles.", "Ideas" },
				{
						"Thomas Edison",
						"Genius is one per cent inspiration, ninety-nine per cent perspiration.",
						"Genius" } };

	}

}
