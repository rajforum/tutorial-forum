class SignUp extends React.Component{
    render(){
        return(
    <div id="signup_page">
        <form action="http://172.22.139.177:8080/tutorial-forum-sof/api/v1/credential/create" method="post">
            <div id="form_data">
                <label for="name">Display Name</label><br/>
                <input type="text" name="name" maxlength="30" required placeholder="R.Kishor" autocomplete="off" /><br/>
                
                <label for="email">Email (required, but never shown)</label><br/>
                <input type="email" name="email" maxlength="60" required placeholder="you@example.org" /><br/>
                
                <label for="mobile_no">Contact No (required, but never shown)</label><br/>
                <input type="number" name="mobile_no" maxlength="10" required placeholder="9999888833" /><br/>
                
                <label for="country">Country</label><br/>
                <input type="text" name="country"  required placeholder="India" /><br/>
                
                <label for="password">Password</label><br/>
                <input type="password" name="password" maxlength="30" minlength="6" required placeholder="*******" /><br/>
                
                <input type="hidden" name="role" value="user"/>

                <div id="form_btn">
                    <input type="submit" value="Sign up" />
                </div>

            </div>
        </form>
         <a href="login.html" title="login">Login</a>
    </div>

        );
    }
}

ReactDOM.render(
    <SignUp />,
    document.getElementById('content')

);
