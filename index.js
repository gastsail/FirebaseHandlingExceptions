const functions = require('firebase-functions');
var admin = require("firebase-admin");

var serviceAccount = require("./gs-test.json");

admin.initializeApp({
  credential: admin.credential.cert(serviceAccount),
  databaseURL: "https://gs-test-609f5.firebaseio.com"
});

// // Create and Deploy Your First Cloud Functions
// // https://firebase.google.com/docs/functions/write-firebase-functions
//
 exports.onItemCreation = functions.firestore.document('purchases/{purchaseId}')
 .onCreate(async(snapshot, context) => {
     const itemDataSnap = await snapshot.ref.get()
     return admin.firestore().collection('mail').add({
        to: [itemDataSnap.data().email],
        message: {
          subject: 'Your reservation is here !',
          html: 'Hey '+ itemDataSnap.data().name +'. This is your reservation for the event and it costs $' + itemDataSnap.data().dollarqty +', thanks for the purchase.',
        }
      }).then(() => console.log('Queued email for delivery!'));
 });
