
    paypal.Button.render({

        /*
        BASIC CLIENT INTEGRATION
        */

        env: 'sandbox', // Specify 'sandbox' for the test environment //'production'

        style: {
            size: 'small',
            color: 'blue',
            shape: 'pill'
        },

         client: {
                    sandbox:    'AcVvSVjCGp_I0xy-fV13D8NoYWkDW6bWxrvPOxPy69URyNxDD85r6mrI1wPV89XFSkUndn_58ssivW7V',
                    production: 'xxxxxxxxx'
         },

        //TODO - redirections
        //https://developer.paypal.com/docs/integration/direct/express-checkout/integration-jsv4/script-options/

        payment: function() {
            // Set up the payment here, when the buyer clicks on the button

             var env    = this.props.env;
             var client = this.props.client;

            return paypal.rest.payment.create(env, client, {
                transactions: [
                    {
                        amount: { total: '100.00', currency: 'USD' }
                    }
                ]
            });

        },


        //commit: true, // Optional: show a 'Pay Now' button in the checkout flow

        onAuthorize: function(data, actions) {
            // Execute the payment here, when the buyer approves the transaction
            // Optional: display a confirmation page here
            return actions.payment.execute().then(function() {
                // Show a success page to the buyer
                console.log(">>> SUCCESS!");
                console.log(data);



            });

       },

        onCancel: function(data, actions) {
             // Show a cancel page or return to cart
            console.log(">>> CANCELED!");
            console.log(data);
        }

    }, '#paypal-button');
