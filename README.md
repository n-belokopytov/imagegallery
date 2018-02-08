# What is imagegallery?
This project is an attempt to make a market-ready Minimum Viable Product of a photo gallery app in less than one week. It's both a sample of my work under pressure and illustrates my approach towards product-first prioritisation.

*Feel free to reuse any of the code here in exchange of giving me feedback on it.*

I did my best to structure code in a way that would later allow modular iteration on the most aspects of it and unit-testing the business logic as soon as the decision is made to carry on with this product direction.

**Keywords:** *Kotlin, Crashlytics, RX, MVP, Firebase, Authentication, Firestore, Cloud Storage, Photo Gallery, Clean Architecture*

## Strengths:
- Authentication with Google Sign-In allows to lessen the friction specifically with Android users
- Empty-state view incentivises users to cross the conversion barrier
- Technical state is monitored by Crashlytics integration
- Simple internal structure keeps codebase accessible for third-party specialists
- Product metrics important for a product launch like retention and engagements can already be monitored with Firebase Analytics 
- Modular architecture with separation of layers and dependency injection makes it easy to iterate and test
- Backend is provided by Firebase, which makes it easy to maintain without hiring specialists

## Weaknesses:
- It's lacking pinch-to-zoom feature, which I anticipate would raise more engagement
- There is no thumbnail loading at this point for the gallery view, which causes hits in performance
- Infinite scrolling is not present due to Firebase complexity in this use-case, the benefit of having it at the launch is small though
- Codebase is not covered in tests, which would cause friction in onboarding new engineers, should be gradually fixed as features get finilised


## Architecture
In terms of architecture I went for two layers - a presentation one and a domain one, IMO it strikes a fine balance between scrappiness and forethought.

### Presentation/Domain
For presentation I picked tried and true MVP pattern, since for MVVM you cannot control the code you produce to the degree it's needed for quick and dirty iterations at first stages of a product launch and MVP is a pattern that is ultimately more familiar to me. Where it made sense I've added a ConstraintLayout - it balances performance with accesibility and has good tooling support.

### Data
For data I went for direct access to Repositories from Presenters and handling business logic on Presenter side. The benefits of this approach are the fact that business logic can still be tested independently from the rest of the app, while not improving the complexity and keeping the iteration speed high. For more complex apps with lots of business logic or for enterprise, it makes sense to add Interactors to deal with business logic instead. Repositories have internal references to sources of data, which can be substituted for own network API or custom DB at any point.

## Learnings
- Firestore has difficulties with pagination of content - will require customisation
- Fabric and Google's integration is still not at the smooth level, which results in contradicting documentation on Crashlytics
- Setting up PHP backend and automating this process for future use has deceptively steep learning curve
