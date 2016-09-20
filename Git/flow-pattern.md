<p>git checkout <feature-branch></p>
<p>git pull</p>
<p>git checkout <release-branch></p>
<p>git pull</p>
<p>git merge --no-ff <feature-branch></p>
<p>git push</p>
<p>git tag -a branch-<feature-branch> -m 'Merge <feature-branch> into <release-branch>'</p>
<p>git push --tags</p>
<p>git branch -d <feature-branch></p>
<p>git push origin :<feature-branch></p>
